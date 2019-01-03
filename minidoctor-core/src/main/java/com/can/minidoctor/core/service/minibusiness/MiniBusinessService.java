package com.can.minidoctor.core.service.minibusiness;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.DateDetailReq;
import com.can.minidoctor.api.dto.request.miniwx.FutureArrangReq;
import com.can.minidoctor.api.dto.request.miniwx.GetDateListReq;
import com.can.minidoctor.api.dto.request.miniwx.MakeAnArrangeReq;
import com.can.minidoctor.api.dto.response.ArrangementResp;
import com.can.minidoctor.api.dto.response.DateMentLisResp;
import com.can.minidoctor.api.dto.response.wxmini.DateDetailResp;
import com.can.minidoctor.api.utils.DateUtils;
import com.can.minidoctor.api.utils.ResultUtils;
import com.can.minidoctor.core.dao.arrangement.MiniArrangeMentDao;
import com.can.minidoctor.core.dao.datement.MiniDateMentDao;
import com.can.minidoctor.core.dao.mapper.MinidoctorDatementMapper;
import com.can.minidoctor.core.entity.MinidoctorArrangement;
import com.can.minidoctor.core.entity.MinidoctorArrangementExample;
import com.can.minidoctor.core.entity.MinidoctorDatement;
import com.can.minidoctor.core.enums.DigitalEnums;
import com.can.minidoctor.core.enums.HospitalEnums;
import com.can.minidoctor.core.enums.SectionTypeEnums;
import com.can.minidoctor.core.enums.YesOrNotEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 21:12 2018/12/18
 */
@Component
public class MiniBusinessService {
    private static final Logger LOGGER= LoggerFactory.getLogger(MiniBusinessService.class);

    @Autowired
    MiniDateMentDao miniDateMentDao;
    @Autowired
    MiniArrangeMentDao miniArrangeMentDao;

    public Result getDateDetail(DateDetailReq req){
        MinidoctorDatement md=miniDateMentDao.getDatementById(req.getDateMentId());
        if(null!=md){
            DateDetailResp resp=new DateDetailResp();
            resp.setDateId(md.getId());
            resp.setHospital(HospitalEnums.getNameByCode(md.getHostpital()));
            resp.setIdentification(md.getIdentification());
            resp.setMobile(md.getMobile());
            resp.setName(md.getName());
            resp.setWorkDate(DateUtils.formatDate(md.getWorkDate())+":"+md.getTimeSection());
            return ResultUtils.getOkResult(resp);
        }
        return ResultUtils.getFailedResult(1,"没有找到");
    }

    public Result getMyDates(GetDateListReq req){
        List<MinidoctorDatement> rets=miniDateMentDao.getDatementByOpenId(req.getMdc_openId());
        if(null==rets||rets.isEmpty()){
            return ResultUtils.getOkResult(new ArrayList<>());
        }
        List<DateMentLisResp> resps=new ArrayList<>();
        StringBuilder stringBuilder;
        for(MinidoctorDatement md:rets){
            DateMentLisResp dmr=new DateMentLisResp();
            stringBuilder=new StringBuilder();
            stringBuilder.append(DateUtils.formatDate(md.getWorkDate())).append("-")
            .append(md.getTimeSection()).append("-")
            .append(HospitalEnums.getNameByCode(md.getHostpital())).append("-")
            .append(md.getName());
            dmr.setName(stringBuilder.toString());
            dmr.setEnable(md.getEnabled());
            dmr.setDateMentId(md.getId());
            resps.add(dmr);
        }
        return ResultUtils.getOkResult(resps);
    }

    public Result makeAnArrangeMemt(MakeAnArrangeReq req){
        String[] arr=req.getWorkDate().split(":");
        if(arr!=null&&arr.length<2){
            return ResultUtils.getFailedResult(1,"error");
        }
        String realDate=arr[0];
        String section=arr[1];
        MinidoctorArrangement arrangement=miniArrangeMentDao.checkIfCanDate(realDate,HospitalEnums.getCodeByName(req.getHosptital()),SectionTypeEnums.getCodeByName(section));
        if(null==arrangement){
            return ResultUtils.getFailedResult(1,"所选时间不可预约");
        }
        boolean ret=miniArrangeMentDao.reduceStock(arrangement,SectionTypeEnums.getCodeByName(section));
        if(!ret){
            return ResultUtils.getFailedResult(1,"预约失败");
        }
        MinidoctorDatement minidoctorDatement=new MinidoctorDatement();
        Date now=new Date();
        minidoctorDatement.setCreateTime(now);
        minidoctorDatement.setEnabled(YesOrNotEnums.Yes.getCode());
        minidoctorDatement.setIdentification(req.getIdentification());
        minidoctorDatement.setMobile(req.getMobile());
        minidoctorDatement.setName(req.getName());
        minidoctorDatement.setOpenId(req.getMdc_openId());
        minidoctorDatement.setTimeSection(section);
        minidoctorDatement.setWorkDate(DateUtils.parseDate(realDate));
        minidoctorDatement.setUpdateTime(now);
        minidoctorDatement.setArrangeId(arrangement.getId());
        minidoctorDatement.setHostpital(HospitalEnums.getCodeByName(req.getHosptital()));
        int cnt=miniDateMentDao.intsertOneDatement(minidoctorDatement);
        if(cnt==1){
            return ResultUtils.getOkResult(true);
        }
        return ResultUtils.getFailedResult(1,"error");
    }

    public Result getFutureArrange(FutureArrangReq req){

        List<MinidoctorArrangement> rets=miniArrangeMentDao.getFutureArrangeMents(new Date(),req.getHospital());
        List<ArrangementResp> resps=getDefaultArrange();

        for(ArrangementResp ar:resps){
            MinidoctorArrangement rightOne=getRightOne(rets,ar.getDate());
            if(null!=rightOne){
                if(DigitalEnums.Zero.getCode()==rightOne.getWorkType()){
                    ar.setBefore(getStrArrange(rightOne.getBeforeNoon()));
                    ar.setAfter(getStrArrange(rightOne.getAfterNoon()));
                    ar.setBeforeName(SectionTypeEnums.beforeNoon.getName());
                    ar.setAfterName(SectionTypeEnums.afterNoon.getName());
                }else {
                    ar.setBefore(getStrArrange(rightOne.getBeforeSupper()));
                    ar.setAfter(getStrArrange(rightOne.getAfterSupper()));
                    ar.setBeforeName(SectionTypeEnums.beforeSupper.getName());
                    ar.setAfterName(SectionTypeEnums.afterSupper.getName());
                }
            }
        }

        return ResultUtils.getOkResult(resps);
    }

    private MinidoctorArrangement getRightOne(List<MinidoctorArrangement> arrangements,String workDate){
        for(MinidoctorArrangement ma:arrangements){
            if(workDate.equals(DateUtils.formatDate(ma.getWorkDate()))){
                return ma;
            }
        }
        return null;
    }

    private List<ArrangementResp> getDefaultArrange(){
        List<ArrangementResp> def=new ArrayList<>();
        Date today=new Date();
        for(int i=0;i<12;i++){
            ArrangementResp ar=new ArrangementResp();
            ar.setDate(DateUtils.formatDate(DateUtils.addDays(today,i)));
            ar.setBeforeName("上午");
            ar.setBefore("未排班");
            ar.setAfterName("下午");
            ar.setAfter("未排班");
            def.add(ar);
        }
        return def;
    }

    private String getStrArrange(Byte arrange){
        if(null==arrange){
            return "休";
        }
        if(0==arrange){
            return "满";
        }
        return arrange.toString();
    }

}

package com.can.minidoctor.core.dao.arrangement;

import com.can.minidoctor.api.utils.DateUtils;
import com.can.minidoctor.core.dao.mapper.MinidoctorArrangementMapper;
import com.can.minidoctor.core.entity.MinidoctorArrangement;
import com.can.minidoctor.core.entity.MinidoctorArrangementExample;
import com.can.minidoctor.core.enums.SectionTypeEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:47 2018/12/17
 */
@Component
public class MiniArrangeMentDao {
    @Autowired
    MinidoctorArrangementMapper mapper;

    public List<MinidoctorArrangement> getFutureArrangeMents(Date date,int hospital){
        MinidoctorArrangementExample example=new MinidoctorArrangementExample();
        example.createCriteria().andWorkDateGreaterThan(new Date()).andWorkDateLessThan(DateUtils.addDays(new Date(),12)).andHostpitalEqualTo(Byte.valueOf(hospital+""));
        return mapper.selectByExample(example);
    }



    /**
     * 判断是否可以预约
     * @param workDate
     * @param hospital
     * @param workType
     * @param sectionType
     * @return
     */
    public MinidoctorArrangement checkIfCanDate(String workDate,byte hospital,byte sectionType){
        MinidoctorArrangementExample example=new MinidoctorArrangementExample();
        MinidoctorArrangementExample.Criteria criteria=example.createCriteria();
        criteria.andWorkDateStrEqualTo(workDate);
        criteria.andHostpitalEqualTo(hospital);
        List<MinidoctorArrangement> rets=mapper.selectByExample(example);
        if(null==rets||rets.isEmpty()){
            return null;
        }
        MinidoctorArrangement arrangement=rets.get(0);
        boolean ret=false;
        if(SectionTypeEnums.beforeNoon.getCode()==sectionType){
            ret=judgeOkOrNot(arrangement.getBeforeNoon());

        }else  if(SectionTypeEnums.afterNoon.getCode()==sectionType){
            ret=judgeOkOrNot(arrangement.getAfterNoon());
        }else if(SectionTypeEnums.beforeSupper.getCode()==sectionType){
            ret=judgeOkOrNot(arrangement.getBeforeSupper());
        }else  if(SectionTypeEnums.afterSupper.getCode()==sectionType){
            ret=judgeOkOrNot(arrangement.getAfterSupper());
        }
        return ret?arrangement:null;
    }

    public boolean reduceStock(MinidoctorArrangement record,byte sectionType){
        if(SectionTypeEnums.beforeNoon.getCode()==sectionType){
            record.setBeforeNoon(Byte.valueOf(record.getBeforeNoon().intValue()-1+""));
        }else  if(SectionTypeEnums.afterNoon.getCode()==sectionType){
            record.setAfterNoon(Byte.valueOf(record.getAfterNoon().intValue()-1+""));
        }else if(SectionTypeEnums.beforeSupper.getCode()==sectionType){
            record.setBeforeSupper(Byte.valueOf(record.getBeforeSupper().intValue()-1+""));
        }else  if(SectionTypeEnums.afterSupper.getCode()==sectionType){
            record.setAfterSupper(Byte.valueOf(record.getAfterSupper().intValue()-1+""));
        }
        int cnt=mapper.updateByPrimaryKey(record);
        return cnt==1;
    }

    public boolean addStock(MinidoctorArrangement record,byte sectionType){
        if(SectionTypeEnums.beforeNoon.getCode()==sectionType){
            record.setBeforeNoon(Byte.valueOf(record.getBeforeNoon().intValue()+1+""));
        }else  if(SectionTypeEnums.afterNoon.getCode()==sectionType){
            record.setAfterNoon(Byte.valueOf(record.getAfterNoon().intValue()+1+""));
        }else if(SectionTypeEnums.beforeSupper.getCode()==sectionType){
            record.setBeforeSupper(Byte.valueOf(record.getBeforeSupper().intValue()+1+""));
        }else  if(SectionTypeEnums.afterSupper.getCode()==sectionType){
            record.setAfterSupper(Byte.valueOf(record.getAfterSupper().intValue()+1+""));
        }
        int cnt=mapper.updateByPrimaryKey(record);
        return cnt==1;
    }

    private boolean judgeOkOrNot(Byte value){
        if(null==value){
            return false;
        }
        return !(value.byteValue() ==0);
    }

    public int insertOne(MinidoctorArrangement one){
        return mapper.insert(one);
    }

    public MinidoctorArrangement getArrangeMentById(Long id){
        return mapper.selectByPrimaryKey(id);
    }
}

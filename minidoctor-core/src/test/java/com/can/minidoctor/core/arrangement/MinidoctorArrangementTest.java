package com.can.minidoctor.core.arrangement;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.utils.DateUtils;
import com.can.minidoctor.core.BasicTest;
import com.can.minidoctor.core.dao.mapper.MinidoctorArrangementMapper;
import com.can.minidoctor.core.entity.MinidoctorArrangement;
import com.can.minidoctor.core.entity.MinidoctorArrangementExample;
import com.can.minidoctor.core.service.wxmini.WxMiniService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:06 2018/12/17
 */
public class MinidoctorArrangementTest extends BasicTest {
    @Autowired
    MinidoctorArrangementMapper mapper;
    @Autowired
    WxMiniService wxMiniService;

    @Test
    public void TestGetWXCode(){
        wxMiniService.getWxaCodeUnlimit("current=1","",60);
        System.out.println();
    }

    @Test
    public void testInsert(){
        Date today=new Date();
        for(int i=0;i<100;i++){
            MinidoctorArrangement arrangement=new MinidoctorArrangement();
            arrangement.setAfterNoon(getArrange());
            arrangement.setBeforeNoon(getArrange());
            arrangement.setAfterSupper(getArrange());
            arrangement.setBeforeSupper(getArrange());
            arrangement.setHostpital(gethospital());
            arrangement.setWorkType(gethospital());
            Date work=DateUtils.addDays(today,i);
            work=getCleanDate(work);
            arrangement.setWorkDate(work);
            arrangement.setWorkDateStr(DateUtils.formatDate(work));
            arrangement.setCreateTime(today);
            mapper.insert(arrangement);
        }
    }

    private Date getCleanDate(Date date){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        // 将时分秒,毫秒域清零
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        return cal1.getTime();
    }

    private Byte gethospital(){
        Random random=new Random();
        int flag=random.nextInt(10);
        if(flag<=8){
            return 0;
        }else {
            return 1;
        }
    }

    private Byte getArrange(){
        Random random=new Random();
        int flag=random.nextInt(10);
        if(flag<2)
            return null;
        else if(flag<4)
            return 0;
        else {
            return Byte.valueOf(random.nextInt(30)+"");
        }

    }
    @Test
    public void getFuture(){
//        Result ret=wxMiniService.getFutureArrange(1);
//        System.out.print("");
    }
}

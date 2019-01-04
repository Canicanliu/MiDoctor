package com.can.minidoctor.core.service.minibusiness;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.utils.DateUtils;
import com.can.minidoctor.api.utils.ResultUtils;
import com.can.minidoctor.core.dao.arrangement.MiniArrangeMentDao;
import com.can.minidoctor.core.entity.MinidoctorArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:39 2019/1/4
 */
@Component
public class IniDataService {
    @Autowired
    MiniArrangeMentDao miniArrangeMentDao;

    public Result iniData(){
        Date today=new Date();
        for(int i=0;i<100;i++){
            MinidoctorArrangement arrangement=new MinidoctorArrangement();
            arrangement.setAfterNoon(getArrange());
            arrangement.setBeforeNoon(getArrange());
            arrangement.setAfterSupper(getArrange());
            arrangement.setBeforeSupper(getArrange());
            arrangement.setHostpital(gethospital());
            arrangement.setWorkType(gethospital());
            Date work= DateUtils.addDays(today,i);
            work=getCleanDate(work);
            arrangement.setWorkDate(work);
            arrangement.setWorkDateStr(DateUtils.formatDate(work));
            arrangement.setCreateTime(today);
            miniArrangeMentDao.insertOne(arrangement);
        }
        return ResultUtils.getOkResult("成功");
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

}

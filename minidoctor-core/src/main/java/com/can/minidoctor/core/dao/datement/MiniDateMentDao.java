package com.can.minidoctor.core.dao.datement;

import com.can.minidoctor.core.dao.mapper.MinidoctorDatementMapper;
import com.can.minidoctor.core.entity.MinidoctorDatement;
import com.can.minidoctor.core.entity.MinidoctorDatementExample;
import com.can.minidoctor.core.enums.YesOrNotEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:26 2018/12/21
 */
@Component
public class MiniDateMentDao {
    @Autowired
    MinidoctorDatementMapper mapper;

    public int intsertOneDatement(MinidoctorDatement record){
        return mapper.insert(record);
    }

    public MinidoctorDatement getDatementById(Long dId){
        return mapper.selectByPrimaryKey(dId);
    }

    public List<MinidoctorDatement> getDatementByOpenId(String openId){
        MinidoctorDatementExample example=new MinidoctorDatementExample();
        example.createCriteria().andOpenIdEqualTo(openId).andEnabledEqualTo(YesOrNotEnums.Yes.getCode());
        List<MinidoctorDatement> res=mapper.selectByExample(example);
        return res;
    }

    public int updateDatement(MinidoctorDatement record){
        return mapper.updateByPrimaryKey(record);
    }
}

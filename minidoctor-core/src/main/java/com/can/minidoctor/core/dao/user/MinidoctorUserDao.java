package com.can.minidoctor.core.dao.user;

import com.can.minidoctor.core.dao.mapper.MinidoctorUserMapper;
import com.can.minidoctor.core.entity.MinidoctorUser;
import com.can.minidoctor.core.entity.MinidoctorUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:58 2019/1/13
 */
@Component
public class MinidoctorUserDao {
    @Autowired
    MinidoctorUserMapper userMapper;

    public int addUser(MinidoctorUser user){
        return userMapper.insert(user);
    }

    public MinidoctorUser getUserByOpenId(String onpenId){
        MinidoctorUserExample example=new MinidoctorUserExample();
        example.createCriteria().andOpenIdEqualTo(onpenId);
        List<MinidoctorUser> users=userMapper.selectByExample(example);
        if(users!=null&&!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

}

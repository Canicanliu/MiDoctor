package com.can.minidoctor.api.utils;

import com.can.minidoctor.api.commons.base.Result;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 14:34 2018/12/8
 */
public class ResultUtils {

    public static int OK=0;

    public static Result getOkResult(Object data){
        Result result=new Result();
        result.setCode(OK);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static Result getFailedResult(int errorCode,String msg){
        Result result=new Result();
        result.setCode(errorCode);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

}

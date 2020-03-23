package com.bysj.wyb.common.result;

/**
 * @author wangyb
 */
public class HandleResult {

    public Result outResultWithData(String status,String message,Object data){
        Result re=new Result();
        if("1".equals(status)){
            re.setStatus("1");
            re.setMessage(message);
            re.setData(data);
        }
        else {
            re.setStatus("0");
            re.setMessage(message);
            re.setData(data);
        }

        return re;
    }

    public Result outResultWithoutData(String status,String message){
        Result re=new Result();
        if("1".equals(status)){
            re.setStatus("1");
            re.setMessage(message);
        }
        else {
            re.setStatus("0");
            re.setMessage(message);
        }

        return re;
    }

}

package nju.software.convoy.controller.ResponseBody;

/**
 * @Author: tommy_z
 * @Date: 2020/1/2
 */
public class ResultFactory {
    // 默认访问成功返回值
    public static Result success(){
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.code);
        r.setMessage(ResultCode.SUCCESS.message);
        r.setData(true);
        return r;
    }

    // 自定义访问成功信息
    public static Result success(String msg){
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.code);
        r.setMessage(msg);
        r.setData(true);
        return r;
    }

    // 自定义访问成功数据
    public static Result success(Object data){
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.code);
        r.setMessage(ResultCode.SUCCESS.message);
        r.setData(data);
        return r;
    }

    // 默认访问失败返回值
    public static Result failed(){
        Result r = new Result();
        r.setCode(ResultCode.FAILED.code);
        r.setMessage(ResultCode.FAILED.message);
        return r;
    }

    // 自定义访问失败信息
    public static Result failed(String msg){
        Result r = new Result();
        r.setCode(ResultCode.FAILED.code);
        r.setMessage(msg);
        return r;
    }

    // 自定义访问失败信息
    public static Result failed(String msg, Object data){
        Result r = new Result();
        r.setCode(ResultCode.FAILED.code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    // 全自定义
    public static Result customize(int code, String msg, Object data){
        Result r = new Result();
        r.setData(data);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static Result authFailed(){
        Result r = new Result();
        r.setCode(ResultCode.AUTH_ERROR.code);
        r.setMessage(ResultCode.AUTH_ERROR.message);
        return r;
    }
}

package nju.software.convoy.controller.ResponseBody;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultCode {
    SUCCESS("访问成功", 200),
    FAILED("访问失败", 400),
    AUTH_ERROR("未授权的访问", 300);

    public final String message;
    public final int code;
}

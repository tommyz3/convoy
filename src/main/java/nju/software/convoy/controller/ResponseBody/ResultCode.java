package nju.software.convoy.controller.ResponseBody;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultCode {
    SUCCESS("访问成功", 200),
    FAILED("访问失败", 400);

    public final String message;
    public final int code;
}

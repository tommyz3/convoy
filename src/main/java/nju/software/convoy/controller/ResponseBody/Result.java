package nju.software.convoy.controller.ResponseBody;

import lombok.*;

/**
 * @Author: tommy_z
 * @Date: 2020/1/2
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Result {
    private int code;
    private String message;
    private Object data;
}

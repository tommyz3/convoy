package nju.software.convoy.controller.RequestBody;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Data
public class AttendanceReq {
    @Size(min = 11, max = 11, message = "手机号码格式不正确")
    private String phone;
    private String name;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;
    @NotNull(message = "部门不能为空")
    private String department;
}

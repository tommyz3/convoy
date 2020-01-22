package nju.software.convoy.controller.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripReq {
    private Byte approve;
    private String department;
    private String address;
    private String reason;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private String cityFrom;
    private String cityTo;
    private String transport;
    @NotNull
    @Size(min = 11, max = 11, message = "手机号码格式不正确")
    private String phone;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
}

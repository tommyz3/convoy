package nju.software.convoy.controller.RequestBody;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/16
 */
@Data
public class HolidayReq {
    private String Phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date end;
    private String approve;
    private String department;
    private String type;
    private String reason;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date time;
}

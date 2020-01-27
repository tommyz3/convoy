package nju.software.convoy.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/27
 */
@Data
public class AttendanceDetailModel {
    // 打卡地点
    private String address;
    // 打卡时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private String name;
    private String phone;
}

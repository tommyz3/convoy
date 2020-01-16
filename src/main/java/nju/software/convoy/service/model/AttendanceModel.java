package nju.software.convoy.service.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Data
public class AttendanceModel {
    private String phone;
    private String name;
    private Date time;
    private String address;
    private int num;
    private Date date;
    private String department;
}

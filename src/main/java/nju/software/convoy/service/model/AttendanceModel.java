package nju.software.convoy.service.model;

import lombok.Data;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Data
public class AttendanceModel {
    private String phone;
    private String name;
    private String time;
    private String address;
    private int num;
    private String date;
    private String department;
}

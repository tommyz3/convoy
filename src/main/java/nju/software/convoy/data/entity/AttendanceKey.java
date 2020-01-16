package nju.software.convoy.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Data
@AllArgsConstructor
public class AttendanceKey {
    private Date date;
    private String department;
}

package nju.software.convoy.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Data
@AllArgsConstructor
public class AttendanceKey {
    private String date;
    private String department;
}

package nju.software.convoy.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 用于根据部门及日期进行查找
 * 不是真正意义上的key
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Data
@AllArgsConstructor
public class AttendanceKey {
    private Date date;
    private String department;
}

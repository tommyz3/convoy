package nju.software.convoy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/4/30
 */
@Data
@AllArgsConstructor
public class DriverStatusKey {
    private String id;
    private Date start;
    private Date end;
}

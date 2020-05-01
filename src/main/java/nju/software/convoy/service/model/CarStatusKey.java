package nju.software.convoy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Data
@AllArgsConstructor
public class CarStatusKey {
    private Integer id;
    private Date start;
    private Date end;
}

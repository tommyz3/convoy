package nju.software.convoy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripModel {
    private Byte approve;
    private String department;
    private String address;
    private String reason;
    private Date time;
    private String cityFrom;
    private String cityTo;
    private String transport;
    private String phone;
    private Date begin;
    private Date end;
}

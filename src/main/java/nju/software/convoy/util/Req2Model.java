package nju.software.convoy.util;

import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.AttendanceReq;
import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.data.entity.Attendance;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.UserModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: tommy_z
 * @Date: 2020/1/6
 * request实体类转换为model实体类
 */
@Slf4j
public class Req2Model {
    public static UserModel turn2UserModel(UserReq r) {
        if (r.getTitle() != null)
            return new UserModel(r.getPhone(), r.getPassword(), r.getName(), r.getDepartment(), Integer.parseInt(r.getTitle()));
        return new UserModel(r.getPhone(), r.getPassword(), r.getName(), r.getDepartment(), 0);
    }

    public static UserModel turn2UserModelWithNewPassword(UserReq r) {
        if (r.getTitle() != null)
            return new UserModel(r.getPhone(), r.getNewPassword(), r.getName(), r.getDepartment(), Integer.parseInt(r.getTitle()));
        return new UserModel(r.getPhone(), r.getNewPassword(), r.getName(), r.getDepartment(), 0);
    }

    public static AttendanceModel turn2AttendanceModel(AttendanceReq r) {
        AttendanceModel attendance = new AttendanceModel();
        // 日期格式yyyy-MM-dd hh:mm:ss
        String date = r.getTime().substring(0, 10);
        attendance.setAddress(r.getAddress());
        attendance.setDate(date);
        attendance.setName(r.getName());
        attendance.setPhone(r.getPhone());
        attendance.setTime(r.getTime());
        attendance.setDepartment(r.getDepartment());
        return attendance;
    }
}

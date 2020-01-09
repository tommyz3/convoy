package nju.software.convoy.util;

import nju.software.convoy.data.entity.Attendance;
import nju.software.convoy.data.entity.AttendanceDetail;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.UserModel;

/**
 * @Author: tommy_z
 * @Date: 2020/1/6
 */
public class Model2Entity {
    public static User turn2UserEntity(UserModel u){
        return new User(u.getPhone(), u.getPassword(), u.getDepartment(), u.getTitle(), u.getName(), u.getTitleDes());
    }

    public static Attendance turn2AttendanceWithId(int id, AttendanceModel a){
        Attendance attendance = new Attendance();
        attendance.setDate(a.getDate());
        attendance.setNum(a.getNum());
        attendance.setDepartment(a.getDepartment());
        attendance.setId(id);
        return attendance;
    }

    public static Attendance turn2Attendance(AttendanceModel a){
        Attendance attendance = new Attendance();
        attendance.setDate(a.getDate());
        attendance.setNum(a.getNum());
        attendance.setDepartment(a.getDepartment());
        return attendance;
    }

    public static AttendanceDetail turn2AttendanceDetail(int id, AttendanceModel a){
        AttendanceDetail detail = new AttendanceDetail();
        detail.setAddress(a.getAddress());
        detail.setName(a.getName());
        detail.setTime(a.getTime());
        detail.setAttId(id);
        detail.setPhone(a.getPhone());
        return detail;
    }
}

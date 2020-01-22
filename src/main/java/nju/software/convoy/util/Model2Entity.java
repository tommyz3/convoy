package nju.software.convoy.util;

import nju.software.convoy.data.entity.*;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.TripModel;
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

    public static Holiday turn2HolidayEntity(HolidayModel h){
        Holiday holiday = new Holiday();
        holiday.setApprove(h.getApprove());
        holiday.setBegin(h.getBegin());
        holiday.setDepartment(h.getDepartment());
        holiday.setEnd(h.getEnd());
        holiday.setPhone(h.getPhone());
        holiday.setReason(h.getReason());
        holiday.setTime(h.getTime());
        holiday.setType(h.getType());
        return holiday;
    }

    public static HolidayKey subHolidayKey(HolidayModel h){
        HolidayKey key = new HolidayKey();
        key.setBegin(h.getBegin());
        key.setEnd(h.getEnd());
        key.setPhone(h.getPhone());
        return key;
    }

    public static UserAuthorityKey subAuthorityKey(UserModel userModel){
        return new UserAuthorityKey(userModel.getDepartment(), userModel.getTitle());
    }

    public static Trip turn2TripEntity(TripModel model){
        Trip trip =  new Trip(model.getApprove(), model.getDepartment(), model.getAddress(), model.getReason(), model.getTime(), model.getCityFrom(),
                model.getCityTo(), model.getTransport());
        trip.setPhone(model.getPhone());
        trip.setBegin(model.getBegin());
        trip.setEnd(model.getEnd());
        return trip;
    }

    public static TripKey subTripKey(TripModel t){
        TripKey key = new TripKey();
        key.setBegin(t.getBegin());
        key.setEnd(t.getEnd());
        key.setPhone(t.getPhone());
        return key;
    }
}

package nju.software.convoy.util;

import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.AttendanceReq;
import nju.software.convoy.controller.RequestBody.HolidayReq;
import nju.software.convoy.controller.RequestBody.TripReq;
import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.TripModel;
import nju.software.convoy.service.model.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: tommy_z
 * @Date: 2020/1/6
 * request实体类转换为model实体类
 */
@Slf4j
public class Req2Model {
    public static UserModel turn2UserModel(UserReq r) {
        if (r.getTitle() != null)
            return new UserModel(r.getPhone(), r.getPassword(), r.getDepartment(), Integer.parseInt(r.getTitle()), r.getName(),  r.getTitleDes());
        return new UserModel(r.getPhone(), r.getPassword(), r.getDepartment(), 0,  r.getName(),r.getTitleDes());
    }

    public static UserModel turn2UserModelWithNewPassword(UserReq r) {
        if (r.getTitle() != null)
            return new UserModel(r.getPhone(), r.getNewPassword(), r.getDepartment(), Integer.parseInt(r.getTitle()), r.getName(),  r.getTitleDes());
        return new UserModel(r.getPhone(), r.getNewPassword(), r.getDepartment(), 0,  r.getName(),r.getTitleDes());
    }

    public static AttendanceModel turn2AttendanceModel(AttendanceReq r) {
        AttendanceModel attendance = new AttendanceModel();
        // 考勤打卡时间格式yyyy-MM-dd hh:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date = null;
        try {
            // 获取考勤打卡日期
            date = sdf.parse(sdf.format(r.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        attendance.setDate(date);
        attendance.setTime(r.getTime());
        attendance.setAddress(r.getAddress());
        attendance.setName(r.getName());
        attendance.setPhone(r.getPhone());
        attendance.setDepartment(r.getDepartment());
        return attendance;
    }

    public static HolidayModel turn2HolidayModel(HolidayReq r){
        byte defaultApprove = 0;
        return new HolidayModel(r.getPhone(), r.getBegin(), r.getEnd(), r.getDepartment(), r.getType(), r.getReason(), r.getTime(), defaultApprove);
    }

    public static HolidayModel turn2HolidayKey(HolidayReq r){
        HolidayModel holidayModel = new HolidayModel();
        holidayModel.setBegin(r.getBegin());
        holidayModel.setEnd(r.getEnd());
        holidayModel.setPhone(r.getPhone());
        return holidayModel;
    }

    public static TripModel turn2TripModel(TripReq t){
        return new TripModel(t.getApprove(), t.getDepartment(), t.getAddress(), t.getReason(),t.getTime(), t.getCityFrom(),
                t.getCityTo(), t.getTransport(), t.getPhone(), t.getBegin(), t.getEnd());
    }
}

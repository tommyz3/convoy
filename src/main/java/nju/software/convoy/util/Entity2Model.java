package nju.software.convoy.util;
import java.util.Date;

import nju.software.convoy.data.entity.*;
import nju.software.convoy.service.model.*;

/**
 * @Author: tommy_z
 * @Date: 2020/1/6
 */
public class Entity2Model {
    public static UserModel entity2UserModel(User user) {
        return new UserModel(user.getPhone(), user.getPassword(), user.getDepartment(), user.getTitle(),user.getName(), user.getTitleDes());
    }

    public static HolidayModel turn2HolidayModel(Holiday h){
        return new HolidayModel(h.getPhone(), h.getBegin(), h.getEnd(), h.getDepartment(), h.getType(), h.getReason(), h.getTime(), h.getApprove());
    }

    public static TripModel turn2TripModel(Trip t){
        return new TripModel(t.getApprove(), t.getDepartment(),t.getAddress(),t.getReason(),t.getTime(),t.getCityFrom(),t.getCityTo(),
                t.getTransport(),t.getPhone(), t.getBegin(),t.getEnd());
    }

    public static AttendanceDetailModel turn2AttendanceDetailModel(AttendanceDetail a){
        AttendanceDetailModel m = new AttendanceDetailModel();
        m.setAddress(a.getAddress());
        m.setTime(a.getTime());
        return m;
    }

    public static AttendanceDetailModel turn2AttendanceDetailWithAll(AttendanceDetail a){
        AttendanceDetailModel m = new AttendanceDetailModel();
        m.setAddress(a.getAddress());
        m.setTime(a.getTime());
        m.setName(a.getName());
        m.setPhone(a.getPhone());
        return m;
    }

}

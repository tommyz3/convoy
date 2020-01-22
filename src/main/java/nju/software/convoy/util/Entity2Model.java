package nju.software.convoy.util;

import nju.software.convoy.data.entity.Holiday;
import nju.software.convoy.data.entity.Trip;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.TripModel;
import nju.software.convoy.service.model.UserModel;

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
}

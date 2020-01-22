package nju.software.convoy.service;

import nju.software.convoy.data.entity.UserAuthorityKey;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.UserModel;

import java.util.List;

public interface HolidayService {
    boolean add(HolidayModel h);
    HolidayModel find(HolidayModel h);
    List<HolidayModel> findHolidayWithAuthority(UserModel userModel);
    boolean update(HolidayModel holidayModel);
}

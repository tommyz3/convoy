package nju.software.convoy.service;

import nju.software.convoy.data.entity.UserAuthorityKey;
import nju.software.convoy.service.model.AttendanceDetailModel;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.UserModel;

import java.util.List;

public interface AttendanceService {
    boolean add(AttendanceModel a);
    List<AttendanceDetailModel> findAll(String phone);
    List<AttendanceDetailModel> findWithAuthority(UserModel user);
}

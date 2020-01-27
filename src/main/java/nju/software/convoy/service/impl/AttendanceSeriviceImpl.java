package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.AttendanceDetailMapper;
import nju.software.convoy.data.dao.AttendanceMapper;
import nju.software.convoy.data.dao.HolidayMapper;
import nju.software.convoy.data.entity.*;
import nju.software.convoy.service.AttendanceService;
import nju.software.convoy.service.model.AttendanceDetailModel;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.UserModel;
import nju.software.convoy.util.Entity2Model;
import nju.software.convoy.util.Model2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Service
public class AttendanceSeriviceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private AttendanceDetailMapper detailMapper;
    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public boolean add(AttendanceModel a) {
        AttendanceKey key = new AttendanceKey(a.getDate(), a.getDepartment());
        Attendance old = attendanceMapper.selectByDateAndDepartment(key);
        if (old != null) {
            a.setNum(old.getNum() + 1);
            updateAttendance(old, a);
        }
        else {
            a.setNum(1);
            attendanceMapper.insert(Model2Entity.turn2Attendance(a));
            old = attendanceMapper.selectByDateAndDepartment(key);
        }
        AttendanceDetail detail = Model2Entity.turn2AttendanceDetail(old.getId(), a);
        int r = detailMapper.insertSelective(detail);
        if (r > 0)
            return true;
        return false;
    }

    @Override
    public List<AttendanceDetailModel> findAll(String phone) {
        List<AttendanceDetail> attendanceDetails = detailMapper.selectByPhone(phone);
        List<AttendanceDetailModel> models = new ArrayList<>(attendanceDetails.size());
        for (AttendanceDetail a: attendanceDetails){
            models.add(Entity2Model.turn2AttendanceDetailModel(a));
        }
        return models;
    }

    @Override
    public List<AttendanceDetailModel> findWithAuthority(UserModel user) {
        UserAuthorityKey key = Model2Entity.subAuthorityKey(user);
        List<AttendanceDetail> details = detailMapper.selectAuthority(key);
        List<AttendanceDetailModel> models = new ArrayList<>(details.size());
        for (AttendanceDetail a: details)
            models.add(Entity2Model.turn2AttendanceDetailWithAll(a));
        return models;
    }

    private void updateAttendance(Attendance old, AttendanceModel model){
        Attendance update = Model2Entity.turn2AttendanceWithId(old.getId(), model);
        attendanceMapper.updateByPrimaryKey(update);
    }

}

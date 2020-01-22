package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.AttendanceDetailMapper;
import nju.software.convoy.data.dao.AttendanceMapper;
import nju.software.convoy.data.dao.HolidayMapper;
import nju.software.convoy.data.entity.Attendance;
import nju.software.convoy.data.entity.AttendanceDetail;
import nju.software.convoy.data.entity.AttendanceKey;
import nju.software.convoy.data.entity.Holiday;
import nju.software.convoy.service.AttendanceService;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.util.Model2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void updateAttendance(Attendance old, AttendanceModel model){
        Attendance update = Model2Entity.turn2AttendanceWithId(old.getId(), model);
        attendanceMapper.updateByPrimaryKey(update);
    }

}

package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.HolidayMapper;
import nju.software.convoy.data.entity.Holiday;
import nju.software.convoy.data.entity.HolidayKey;
import nju.software.convoy.data.entity.UserAuthorityKey;
import nju.software.convoy.service.HolidayService;
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
 * @Date: 2020/1/22
 */
@Service
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public boolean add(HolidayModel h) {
        Holiday holiday = Model2Entity.turn2HolidayEntity(h);
        int result = holidayMapper.insert(holiday);
        if (result > 0)
            return true;
        return false;
    }

    @Override
    public HolidayModel find(HolidayModel h) {
        HolidayKey key = Model2Entity.subHolidayKey(h);
        Holiday holiday = holidayMapper.selectByPrimaryKey(key);
        if (null == holiday)
            return null;
        return Entity2Model.turn2HolidayModel(holiday);
    }

    @Override
    public List<HolidayModel> findHolidayWithAuthority(UserModel userModel) {
        UserAuthorityKey key = Model2Entity.subAuthorityKey(userModel);
        List<Holiday> holidays = holidayMapper.selectAuthority(key);
        List<HolidayModel> models = new ArrayList<>(holidays.size());
        for (Holiday h: holidays){
            models.add(Entity2Model.turn2HolidayModel(h));
        }
        return models;
    }

    @Override
    public boolean update(HolidayModel model){
        Holiday holiday = Model2Entity.turn2HolidayEntity(model);
        int update = holidayMapper.updateByPrimaryKeySelective(holiday);
        if (update > 0)
            return true;
        return false;
    }
}

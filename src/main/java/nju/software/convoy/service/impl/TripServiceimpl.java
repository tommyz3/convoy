package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.TripMapper;
import nju.software.convoy.data.entity.Trip;
import nju.software.convoy.data.entity.TripKey;
import nju.software.convoy.data.entity.UserAuthorityKey;
import nju.software.convoy.service.TripSerivice;
import nju.software.convoy.service.model.TripModel;
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
public class TripServiceimpl implements TripSerivice {
    @Autowired
    private TripMapper tripMapper;

    @Override
    public boolean add(TripModel t) {
        Trip trip = Model2Entity.turn2TripEntity(t);
        int add = tripMapper.insertSelective(trip);
        if (add > 0)
            return true;
        return false;
    }

    @Override
    public TripModel find(TripModel model) {
        TripKey key = Model2Entity.subTripKey(model);
        Trip trip = tripMapper.selectByPrimaryKey(key);
        if (null == trip)
            return null;
        return Entity2Model.turn2TripModel(trip);
    }

    @Override
    public List<TripModel> findTripWithAuthority(UserModel userModel) {
        UserAuthorityKey key = Model2Entity.subAuthorityKey(userModel);
        List<Trip> trips = tripMapper.selectAuthority(key);
        List<TripModel> models = new ArrayList<>(trips.size());
        for (Trip t: trips) {
            models.add(Entity2Model.turn2TripModel(t));
        }
        return models;
    }

    @Override
    public boolean update(TripModel tripModel) {
        Trip trip = Model2Entity.turn2TripEntity(tripModel);
        int update = tripMapper.updateByPrimaryKeySelective(trip);
        return update > 0;
    }
}

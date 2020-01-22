package nju.software.convoy.service;

import nju.software.convoy.service.model.TripModel;
import nju.software.convoy.service.model.UserModel;

import java.util.List;

public interface TripSerivice {
    boolean add(TripModel t);
    TripModel find(TripModel h);
    List<TripModel> findTripWithAuthority(UserModel userModel);
    boolean update(TripModel tripModel);
}

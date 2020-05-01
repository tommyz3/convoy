package nju.software.convoy.service;

import nju.software.convoy.data.entity.User;

import java.util.List;

public interface DriverService {
    List<User> recommend(int applyID);
}

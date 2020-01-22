package nju.software.convoy.service;

import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.model.UserModel;

import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/1/2
 */
public interface UserService {
    UserModel findByPhone(String phone);
    boolean add(UserModel user);
    boolean login(UserModel user);
    boolean updatePassword(UserModel user);
    List<UserModel> findAll(UserModel user);
    boolean save(UserModel user);
}

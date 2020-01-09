package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.UserMapper;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.UserService;
import nju.software.convoy.service.model.UserModel;
import nju.software.convoy.util.Entity2Model;
import nju.software.convoy.util.Model2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/1/2
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByPhone(String phone){
        return userMapper.selectByPrimaryKey(phone);
    }

    @Override
    public boolean add(UserModel user) {
        User u = Model2Entity.turn2UserEntity(user);
        int result = userMapper.insertSelective(u);
        if (result > 0)
            return true;
        return false;
    }

    @Override
    public boolean login(UserModel user) {
        User uDB = userMapper.selectByPrimaryKey(user.getPhone());
        if (user.getPassword().equals(uDB.getPassword()))
            return true;
        return false;
    }

    @Override
    public boolean updatePassword(UserModel user) {
        int update = userMapper.updateByPrimaryKeySelective(Model2Entity.turn2UserEntity(user));
        if (update > 0)
            return true;
        return false;
    }

    @Override
    public List<UserModel> findAll(UserModel user) {
        String department = user.getDepartment();
        List<User> users = userMapper.selectByDepartment(department);
        List<UserModel> r = new ArrayList<>();
        for (User u: users) {
            r.add(Entity2Model.entity2UserModel(u));
        }
        return r;
    }

    @Override
    public boolean save(UserModel user) {
        User u = Model2Entity.turn2UserEntity(user);
        int r = userMapper.updateByPrimaryKeySelective(u);
        if (r > 0)
            return true;
        return false;
    }
}

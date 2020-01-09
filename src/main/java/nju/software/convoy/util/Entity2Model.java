package nju.software.convoy.util;

import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.model.UserModel;

/**
 * @Author: tommy_z
 * @Date: 2020/1/6
 */
public class Entity2Model {
    public static UserModel entity2UserModel(User user) {
        return new UserModel(user.getPhone(), user.getPassword(), user.getName(), user.getDepartment(), user.getTitle());
    }
}

package nju.software.convoy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import nju.software.convoy.data.entity.User;


/**
 * @Author: tommy_z
 * @Date: 2020/1/3
 */
@Data
@AllArgsConstructor
public class UserModel {
    private String phone;
    private String password;
    private String name;
    private String department;
    private int title;


}

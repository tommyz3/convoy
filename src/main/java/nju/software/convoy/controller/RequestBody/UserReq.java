package nju.software.convoy.controller.RequestBody;

import lombok.Data;
import lombok.ToString;
import nju.software.convoy.service.model.UserModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author: tommy_z
 * @Date: 2020/1/2
 */
@ToString
@Data
public class UserReq {
    @Size(min = 11, max = 11, message = "手机号码格式不正确")
    private String phone;
    @NotNull(message = "密码不能为空")
    private String password;
//    @NotNull(message = "姓名不能为空")
    private String name;
    private String department;
    private String title;
    private String newPassword;
    private byte[] img;
    private String titleDes;
}

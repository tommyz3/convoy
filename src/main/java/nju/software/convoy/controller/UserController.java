package nju.software.convoy.controller;

import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.UserService;
import nju.software.convoy.service.model.UserModel;
import nju.software.convoy.util.JwtUtil;
import nju.software.convoy.util.Req2Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Author: tommy_z
 * @Date: 2020/1/2
 */
@Slf4j
@RestController("/user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    public Result findByPhone(String phone) {
        UserModel u =  userService.findByPhone(phone);
        return ResultFactory.success(u);
    }


    @RequestMapping("/add")
    public Result add(@Valid UserReq userReq, BindingResult bindingResult) {
        if (userService.findByPhone(userReq.getPhone()) != null)
            return ResultFactory.failed("手机号已注册");
        boolean result = userService.add(Req2Model.turn2UserModel(userReq));
        if (result) {
            String token = JwtUtil.sign(userReq.getPhone());
            return ResultFactory.success(token);
        }
        return ResultFactory.failed("注册失败");
    }

    @RequestMapping("/login")
    public Result login(@Valid UserReq req, BindingResult bindingResult){
        if (userService.findByPhone(req.getPhone()) == null)
            return ResultFactory.failed("用户未注册", req);
        boolean result = userService.login(Req2Model.turn2UserModel(req));
        if (result) {
            String token = JwtUtil.sign(req.getPhone());
            return ResultFactory.success((Object) token);
        }
        else return ResultFactory.failed("密码错误", req);
    }

    @RequestMapping("/modPassword")
    public Result modPassword(@Valid UserReq req, BindingResult bindingResult){
        if (userService.findByPhone(req.getPhone()) == null)
            return ResultFactory.failed("用户未注册");
        boolean password = userService.login(Req2Model.turn2UserModel(req));
        UserModel newUser = Req2Model.turn2UserModelWithNewPassword(req);
        boolean result = userService.updatePassword(newUser);
        if (result)
            return ResultFactory.success("修改成功");
        return ResultFactory.failed("修改失败");
    }

    @RequestMapping("/save")
    public Result save(@Valid UserReq req, BindingResult bindingResult){
        UserModel user = Req2Model.turn2UserModel(req);
        boolean result = userService.save(user);
        if (result)
            return ResultFactory.success(userService.findByPhone(req.getPhone()));
        return ResultFactory.failed("修改失败", userService.findByPhone(req.getPhone()));
    }
}

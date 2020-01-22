package nju.software.convoy.controller;

import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.service.UserService;
import nju.software.convoy.service.model.UserModel;
import nju.software.convoy.util.Req2Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Slf4j
@RestController("/book")
@RequestMapping("/book")
public class BookController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public Result findAll(@Valid UserReq req, BindingResult bindingResult){
        UserModel user = Req2Model.turn2UserModel(req);
        List<UserModel> partner = userService.findAll(user);
        if (partner != null && !partner.isEmpty())
            return ResultFactory.success(partner);
        return ResultFactory.failed("查询失败");
    }
}

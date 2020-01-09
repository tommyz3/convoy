package nju.software.convoy.controller;

import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.AttendanceReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.data.entity.Attendance;
import nju.software.convoy.service.AttendanceService;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.util.Req2Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: tommy_z
 * @Date: 2020/1/7
 */
@Slf4j
@RestController("/attendance")
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/addFixed")
    public Result addFixed(@Valid AttendanceReq attendanceReq, BindingResult bindingResult){
        log.info("用户" + attendanceReq.toString() + "请求添加考勤记录");
        if (bindingResult.hasErrors()){
            log.warn("请求添加考勤参数错误：" + bindingResult.getFieldError());
            return ResultFactory.failed(bindingResult.getFieldError().getDefaultMessage());
        }
        AttendanceModel attendanceModel = Req2Model.turn2AttendanceModel(attendanceReq);
        boolean result = attendanceService.add(attendanceModel);
        if (result)
            return ResultFactory.success("打卡成功");
        return ResultFactory.failed("打卡失败");
    }

    @RequestMapping("/addFlow")
    public Result addFlow(@Valid AttendanceReq attendanceReq, BindingResult bindingResult){
        return addFixed(attendanceReq, bindingResult);
    }
}

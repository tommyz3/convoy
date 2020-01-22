package nju.software.convoy.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.AttendanceReq;
import nju.software.convoy.controller.RequestBody.HolidayReq;
import nju.software.convoy.controller.RequestBody.TripReq;
import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.service.AttendanceService;
import nju.software.convoy.service.HolidayService;
import nju.software.convoy.service.TripSerivice;
import nju.software.convoy.service.UserService;
import nju.software.convoy.service.model.AttendanceModel;
import nju.software.convoy.service.model.HolidayModel;
import nju.software.convoy.service.model.TripModel;
import nju.software.convoy.service.model.UserModel;
import nju.software.convoy.util.JwtUtil;
import nju.software.convoy.util.Req2Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private UserService userService;
    @Autowired
    private TripSerivice tripSerivice;

    @RequestMapping("/addFixed")
    public Result addFixed(@Valid AttendanceReq attendanceReq, BindingResult bindingResult){
        AttendanceModel attendanceModel = Req2Model.turn2AttendanceModel(attendanceReq);
        boolean result = attendanceService.add(attendanceModel);
        if (result)
            return ResultFactory.success("打卡成功");
        return ResultFactory.failed("打卡失败", attendanceModel);
    }

    @RequestMapping("/addFlow")
    public Result addFlow(@Valid AttendanceReq attendanceReq, BindingResult bindingResult){
        return addFixed(attendanceReq, bindingResult);
    }

    @RequestMapping("/addHoliday")
    public Result addHoliday(@Valid HolidayReq holidayReq, BindingResult bindingResult){
        HolidayModel holidayModel = Req2Model.turn2HolidayModel(holidayReq);
        boolean success = holidayService.add(holidayModel);
        if (success)
            return ResultFactory.success(holidayModel);
        return ResultFactory.failed("申请请假失败", holidayModel);
    }

    @RequestMapping("/findHoliday")
    public Result findHoliday(@Valid HolidayReq req, BindingResult bindingResult){
        HolidayModel holidayModel = Req2Model.turn2HolidayModel(req);
        HolidayModel comHolidayModel = holidayService.find(holidayModel);
        if (null != comHolidayModel)
            return ResultFactory.success(comHolidayModel);
        return ResultFactory.failed("查询失败");
    }

    @RequestMapping("/findApproveHoliday")
    public Result findApproveHoliday(HttpServletRequest request){
        String token = request.getHeader("accessToken");
        if (null == token)
            return ResultFactory.failed("请先登录");
        String phone = JwtUtil.getPhone(token);
        UserModel user = userService.findByPhone(phone);
        List<HolidayModel> result = holidayService.findHolidayWithAuthority(user);
        if (null == result)
            return ResultFactory.failed("没有权限");
        return ResultFactory.success(result);
    }

    /**
     * findApproveHoliday接口不用token的测试版
     * @param u
     * @return
     */
    @RequestMapping("/test")
    public Result testfindApproveHoliday(UserReq u){
        List<HolidayModel> result = holidayService.findHolidayWithAuthority(Req2Model.turn2UserModel(u));
        if (null == result)
            return ResultFactory.failed("没有请假申请");
        return ResultFactory.success(result);
    }

    @RequestMapping("/approveHoliday")
    public Result approveHoliday(@Valid HolidayReq req, BindingResult bindingResult){
        HolidayModel model = Req2Model.turn2HolidayModel(req);
        if (null == holidayService.find(model))
            return ResultFactory.failed("不存在此申请");
        byte approve = 1;
        model.setApprove(approve);
        boolean result = holidayService.update(model);
        if (result)
            return ResultFactory.success("审批成功");
        return ResultFactory.failed("审批失败");
    }

    @RequestMapping("/addTrip")
    public Result addTrip(@Valid TripReq req, BindingResult bindingResult){
        TripModel model = Req2Model.turn2TripModel(req);
        boolean add = tripSerivice.add(model);
        if (add)
            return ResultFactory.success(model);
        return ResultFactory.failed("申请出差失败", model);
    }


}

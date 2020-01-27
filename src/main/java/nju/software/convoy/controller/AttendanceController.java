package nju.software.convoy.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.AttendanceReq;
import nju.software.convoy.controller.RequestBody.HolidayReq;
import nju.software.convoy.controller.RequestBody.TripReq;
import nju.software.convoy.controller.RequestBody.UserReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.data.entity.Trip;
import nju.software.convoy.service.AttendanceService;
import nju.software.convoy.service.HolidayService;
import nju.software.convoy.service.TripSerivice;
import nju.software.convoy.service.UserService;
import nju.software.convoy.service.model.*;
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
    public Result addFixed(@Valid AttendanceReq attendanceReq, BindingResult bindingResult) {
        AttendanceModel attendanceModel = Req2Model.turn2AttendanceModel(attendanceReq);
        boolean result = attendanceService.add(attendanceModel);
        if (result)
            return ResultFactory.success("打卡成功");
        return ResultFactory.failed("打卡失败", attendanceModel);
    }

    @RequestMapping("/addFlow")
    public Result addFlow(@Valid AttendanceReq attendanceReq, BindingResult bindingResult) {
        return addFixed(attendanceReq, bindingResult);
    }

    @RequestMapping("/addHoliday")
    public Result addHoliday(@Valid HolidayReq holidayReq, BindingResult bindingResult) {
        HolidayModel holidayModel = Req2Model.turn2HolidayModel(holidayReq);
        boolean success = holidayService.add(holidayModel);
        if (success)
            return ResultFactory.success(holidayModel);
        return ResultFactory.failed("申请请假失败", holidayModel);
    }

    @RequestMapping("/findHoliday")
    public Result findHoliday(@Valid HolidayReq req, BindingResult bindingResult) {
        HolidayModel holidayModel = Req2Model.turn2HolidayModel(req);
        HolidayModel comHolidayModel = holidayService.find(holidayModel);
        if (null != comHolidayModel)
            return ResultFactory.success(comHolidayModel);
        return ResultFactory.failed("查询失败");
    }

    @RequestMapping("/findApproveHoliday")
    public Result findApproveHoliday(HttpServletRequest request) {
        UserModel user = getUserFromToken(request);
        if (null == user)
            return ResultFactory.failed("请先登录");
        List<HolidayModel> result = holidayService.findHolidayWithAuthority(user);
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有权限");
        return ResultFactory.success(result);
    }

    /**
     * findApproveHoliday接口不用token的测试版
     *
     * @param u
     * @return
     */
    @RequestMapping("/testHoliday")
    public Result testfindApproveHoliday(UserReq u) {
        List<HolidayModel> result = holidayService.findHolidayWithAuthority(Req2Model.turn2UserModel(u));
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有请假申请");
        return ResultFactory.success(result);
    }

    @RequestMapping("/approveHoliday")
    public Result approveHoliday(@Valid HolidayReq req, BindingResult bindingResult) {
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
    public Result addTrip(@Valid TripReq req, BindingResult bindingResult) {
        TripModel model = Req2Model.turn2TripModel(req);
        boolean add = tripSerivice.add(model);
        if (add)
            return ResultFactory.success(model);
        return ResultFactory.failed("申请出差失败", model);
    }

    @RequestMapping("/findApproveTrip")
    public Result findApproveTrip(HttpServletRequest request) {
        UserModel user = getUserFromToken(request);
        if (null == user)
            return ResultFactory.failed("请先登录");
        List<TripModel> result = tripSerivice.findTripWithAuthority(user);
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有权限");
        return ResultFactory.success(result);
    }

    /**
     * findApproveTrip接口不用token的测试版
     *
     * @param u
     * @return
     */
    @RequestMapping("/testTrip")
    public Result testfindApproveTrip(UserReq u) {
        List<TripModel> result = tripSerivice.findTripWithAuthority(Req2Model.turn2UserModel(u));
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有出差申请");
        return ResultFactory.success(result);
    }

    @RequestMapping("/findTrip")
    public Result findTrip(@Valid TripReq tripReq, BindingResult bindingResult){
        TripModel tripModel = Req2Model.turn2TripModel(tripReq);
        tripModel = tripSerivice.find(tripModel);
        if (null != tripModel)
            return ResultFactory.success(tripModel);
        return ResultFactory.failed("查询失败");
    }

    @RequestMapping("/approveTrip")
    public Result approveTrip(@Valid TripReq tripReq, BindingResult bindingResult){
        TripModel model = Req2Model.turn2TripModel(tripReq);
        if (null == tripSerivice.find(model))
            return ResultFactory.failed("不存在此申请");
        byte approve = 1;
        model.setApprove(approve);
        boolean result = tripSerivice.update(model);
        if (result)
            return ResultFactory.success("审批成功");
        return ResultFactory.failed("审批失败");
    }

    /**
     * 查看当前用户所有考勤记录
     * @param request
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(HttpServletRequest request){
        String phone = getPhoneFromToken(request);
        List<AttendanceDetailModel> result = attendanceService.findAll(phone);
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有考勤记录");
        return ResultFactory.success(result);
    }

    /**
     * 查看当前用户所有考勤记录不使用token的测试版
     * @param phone
     * @return
     */
    @RequestMapping("/findAlltest")
    public Result findAlltest(String phone){
        List<AttendanceDetailModel> result = attendanceService.findAll(phone);
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有考勤记录");
        return ResultFactory.success(result);
    }

    @RequestMapping("/findOthers")
    public Result findOthers(HttpServletRequest request){
        UserModel user = getUserFromToken(request);
        List<AttendanceDetailModel> result = attendanceService.findWithAuthority(user);
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有权限");
        return ResultFactory.success(result);
    }

    @RequestMapping("/findOtherstest")
    public Result findOthersTest(String phone){
        UserModel user = userService.findByPhone(phone);
        List<AttendanceDetailModel> result = attendanceService.findWithAuthority(user);
        if (null == result || result.isEmpty())
            return ResultFactory.failed("没有权限");
        return ResultFactory.success(result);
    }

    private UserModel getUserFromToken(HttpServletRequest request){
        String phone = getPhoneFromToken(request);
        return userService.findByPhone(phone);
    }

    private String getPhoneFromToken(HttpServletRequest request){
        String token = request.getHeader("accessToken");
        if (null == token)
            return null;
        return JwtUtil.getPhone(token);
    }
}

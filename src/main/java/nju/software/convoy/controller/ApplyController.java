package nju.software.convoy.controller;

import com.github.pagehelper.PageInfo;
import nju.software.convoy.controller.RequestBody.ApplyReq;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.data.entity.ApplyCar;
import nju.software.convoy.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@RestController
@RequestMapping("apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    // 添加申请单
    @RequestMapping("/add")
    public Result addApply(@Valid ApplyReq apply, BindingResult bindingResult){
        ApplyCar applyCar = toEntity(apply);
        applyCar = applyService.add(applyCar);
        return applyCar != null? ResultFactory.success(applyCar): ResultFactory.failed();
    }

    // 取消申请
    @RequestMapping("/cancel")
    public Result cancel(Integer id){
        if (id == null)
            return ResultFactory.failed("参数错误");
        return applyService.del(id) ? ResultFactory.success(): ResultFactory.failed();
    }

    // 获取全部申请
    @RequestMapping("/getAll")
    public Result getAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        PageReq p = new PageReq(page, size);
        PageInfo pageInfo = applyService.getAll(p);
        return ResultFactory.success(pageInfo);
    }

    // 获取某用户全部申请
    @RequestMapping("/getMyAll")
    public Result getMyAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                           @RequestParam(value = "who") String who){
        PageReq p = new PageReq(page, size);
        PageInfo pageInfo = applyService.getMyAll(p, who);
        return ResultFactory.success(pageInfo);
    }

    // 车队领导审批时 确认了车辆和司机后调用
    // 更新申请单数据，添加司机和车辆
    @RequestMapping("/update")
    public Result update(@Valid ApplyReq apply, BindingResult bindingResult){
        boolean updated = applyService.update(toEntity(apply));
        if (updated)
            return ResultFactory.success();
        return ResultFactory.failed();
    }

    private ApplyCar toEntity(ApplyReq r){
        ApplyCar applyCar = new ApplyCar();
        applyCar.setCarKind(r.getCarKind());
        applyCar.setWho(r.getWho());
        applyCar.setEndPosition(r.getEndPosition());
        applyCar.setEndTime(r.getEndTime());
        applyCar.setFollow(r.getFollow());
        applyCar.setReason(r.getReason());
        applyCar.setStartPosition(r.getStartPosition());
        applyCar.setStartTime(r.getStartTime());
        if (r.getCar() != 0)
            applyCar.setCar(r.getCar());
        if (r.getDriver() != null && !r.getDriver().isEmpty())
            applyCar.setDriver(r.getDriver());
        return applyCar;
    }
}

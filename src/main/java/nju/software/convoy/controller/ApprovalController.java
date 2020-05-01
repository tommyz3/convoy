package nju.software.convoy.controller;

import com.github.pagehelper.PageInfo;
import nju.software.convoy.controller.RequestBody.ApprovalReq;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.data.entity.ApprovalCar;
import nju.software.convoy.service.ApplyService;
import nju.software.convoy.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: tommy_z
 * @Date: 2020/4/29
 */
@RestController
@RequestMapping("/approval")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private ApplyService applyService;

    // 添加审批
    @RequestMapping("/add")
    public Result add(@Valid ApprovalReq req, BindingResult bindingResult){
        ApprovalCar a = toEntity(req);
        int id = approvalService.add(a);
        a = approvalService.findByID(id);
        return ResultFactory.success(a);
    }

    // 获取某领导（who）的待审批列表
    @RequestMapping("/getMyAll")
    public Result getAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                         @RequestParam(value = "who") String who){
        PageReq p = new PageReq(page, size);
        PageInfo list = applyService.getWhosAll(p, who);
        return ResultFactory.success(list);
    }



    private ApprovalCar toEntity(ApprovalReq r){
        ApprovalCar a = new ApprovalCar();
        a.setApplyId(r.getApplyID());
        a.setLeader(r.getWho());
        a.setReason(r.getReason());
        a.setStatus(r.getStatus());
        return a;
    }
}

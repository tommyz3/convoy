package nju.software.convoy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.data.dao.ApplyCarMapper;
import nju.software.convoy.data.dao.UserMapper;
import nju.software.convoy.data.entity.ApplyCar;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyCarMapper applyCarMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ApplyCar add(ApplyCar applyCar) {
        int id = applyCarMapper.insertSelective(applyCar);
        ApplyCar newApply = new ApplyCar();
        if (id >= 0)
            newApply = applyCarMapper.selectByPrimaryKey(id);
        return newApply;
    }

    @Override
    public boolean del(Integer id) {
        return applyCarMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean approve(ApplyCar applyCar) {
        return false;
    }

    @Override
    public PageInfo getAll(PageReq page) {
        PageHelper.startPage(page);
        List<ApplyCar> applyCars = applyCarMapper.selectAll();
        return new PageInfo<>(applyCars);
    }

    @Override
    public PageInfo getMyAll(PageReq pageReq, String who) {
        PageHelper.startPage(pageReq);
        List<ApplyCar> applyCars = applyCarMapper.selectAllByPhone(who);
        return new PageInfo<>(applyCars);
    }

    @Override
    public PageInfo getWhosAll(PageReq page, String who) {
        User leader = userMapper.selectByPrimaryKey(who);
        PageHelper.startPage(page);
        List<ApplyCar> notApprovaled = new ArrayList<>();
        if (leader.getDepartment().equals("运输部")) {
            // 获取所有被部门领导审批通过的申请单
            // 即申请单状态为1
            notApprovaled = applyCarMapper.selectByStatus(1);
        } else {
            // 获取所有没被审批过的申请单
            // 即申请单状态为0
            notApprovaled = applyCarMapper.selectByStatus(0);
        }
        return new PageInfo<>(notApprovaled);
    }

    @Override
    public boolean update(ApplyCar applyCar) {
        int i = applyCarMapper.updateByPrimaryKeySelective(applyCar);
        return i > 0;
    }
}

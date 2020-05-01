package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.ApplyCarMapper;
import nju.software.convoy.data.dao.ApprovalCarMapper;
import nju.software.convoy.data.dao.UserMapper;
import nju.software.convoy.data.entity.ApplyCar;
import nju.software.convoy.data.entity.ApprovalCar;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tommy_z
 * @Date: 2020/4/29
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {
    @Autowired
    private ApprovalCarMapper approvalCarMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApplyCarMapper applyCarMapper;

    @Override
    public int add(ApprovalCar a) {
        String who = a.getLeader();
        User leader = userMapper.selectByPrimaryKey(who);
        boolean isDriverLeader = leader.getDepartment().endsWith("运输部");
        a.setKind(isDriverLeader? (byte)1: (byte)0);
        int id = approvalCarMapper.insertSelective(a);
        // 修改申请单状态
        ApplyCar applyCar = applyCarMapper.selectByPrimaryKey(a.getApplyId());
        if (isDriverLeader){// 车队领导
            if (a.getStatus() == 1)// 通过
                applyCar.setStatus(1);
            else applyCar.setStatus(-1);
        }else {// 用车申请人领导
            if (a.getStatus() == 1)// 通过
                applyCar.setStatus(2);
            else applyCar.setStatus(-2);
        }
        applyCarMapper.updateByPrimaryKey(applyCar);
        return id;
    }

    @Override
    public int mod(ApprovalCar a) {
        return approvalCarMapper.updateByPrimaryKey(a);
    }

    @Override
    public ApprovalCar findByID(Integer id) {
        return approvalCarMapper.selectByPrimaryKey(id);
    }
}

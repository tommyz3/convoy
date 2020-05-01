package nju.software.convoy.service;

import nju.software.convoy.data.entity.ApprovalCar;

public interface ApprovalService {
    int add(ApprovalCar a);
    int mod(ApprovalCar a);
    ApprovalCar findByID(Integer id);
}

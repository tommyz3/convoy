package nju.software.convoy.service;

import com.github.pagehelper.PageInfo;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.data.entity.ApplyCar;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
public interface ApplyService {
    ApplyCar add(ApplyCar applyCar);
    boolean del(Integer id);
    boolean approve(ApplyCar applyCar);
    PageInfo getAll(PageReq page);
    PageInfo getMyAll(PageReq pageReq, String who);
    PageInfo getWhosAll(PageReq page, String who);
}

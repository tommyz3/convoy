package nju.software.convoy.service.impl;

import nju.software.convoy.data.dao.ApplyCarMapper;
import nju.software.convoy.data.dao.BusyDriverMapper;
import nju.software.convoy.data.dao.UserMapper;
import nju.software.convoy.data.entity.ApplyCar;
import nju.software.convoy.data.entity.BusyDriver;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.DriverService;
import nju.software.convoy.service.model.DriverStatusKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BusyDriverMapper busyDriverMapper;
    @Autowired
    private ApplyCarMapper applyCarMapper;

    @Override
    public List<User> recommend(int applyID) {
        ApplyCar apply = applyCarMapper.selectByPrimaryKey(applyID);
        // 具体车型与驾照类型对照关系还不清楚，暂定都是驾照种类1
        List<User> driverBackup = userMapper.selectByLicense(1);
        // 获取派车单时间
        Date start = apply.getStartTime(), end = apply.getEndTime();
        List<User> drivers = new ArrayList<>();
        List<Long> diffs = new LinkedList<>();
        // 转换类型，用来计算时间差
        long startLong = start.getTime(), endLong = end.getTime();
        for (int i = 0; i < driverBackup.size(); i++) {
            String cur = driverBackup.get(i).getPhone();
            // 根据司机和时间构造查询条件
            DriverStatusKey key = new DriverStatusKey(cur, start, end);
            // 查询派车单要求时间内是否有任务
            List<BusyDriver> busy = busyDriverMapper.selectBusy(key);
            if (busy == null || busy.size() == 0){
                // 没有任务的话，获取当前司机的全部任务
                List<BusyDriver> curBusy = busyDriverMapper.selectByDriver(cur);
                long minDiff = Long.MAX_VALUE;
                int j = 0;
                // 求当前司机距离任务运行时间段的最小差
                for (; j < curBusy.size(); j++) {
                    long busyStart = curBusy.get(i).getStart().getTime();
                    long busyEnd = curBusy.get(i).getEnd().getTime();
                    long diff = 0;
                    if (busyEnd < endLong)
                        diff = Math.abs(endLong - busyEnd);
                    else diff = Math.abs(busyStart - startLong);
                    minDiff = minDiff < diff? minDiff : diff;
                }
                j = drivers.size() - 1;
                // 排序当前司机与其他符合条件司机的空闲时间差
                for (; j >= 0 ; j--) {
                    if(diffs.get(j) > minDiff) {
                        diffs.add(j, minDiff);
                        break;
                    }
                }
                drivers.add(j, userMapper.selectByPrimaryKey(cur));
            }
        }
        return drivers.subList(0, 5);
    }
}

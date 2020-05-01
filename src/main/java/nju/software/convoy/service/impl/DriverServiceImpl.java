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
        Date start = apply.getStartTime(), end = apply.getEndTime();
        List<User> drivers = new ArrayList<>();
        for (int i = 0; i < driverBackup.size() || drivers.size() < 5; i++) {
            DriverStatusKey key = new DriverStatusKey(driverBackup.get(i).getPhone(), start, end);
            List<BusyDriver> busy = busyDriverMapper.selectBusy(key);
            if (busy == null || busy.size() == 0)
                drivers.add(driverBackup.get(i));
        }
        return drivers;
    }
}

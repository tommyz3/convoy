package nju.software.convoy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.data.dao.*;
import nju.software.convoy.data.entity.*;
import nju.software.convoy.service.ApplyService;
import nju.software.convoy.service.CarService;
import nju.software.convoy.controller.RequestBody.CarCondition;
import nju.software.convoy.service.model.CarStatusKey;
import nju.software.convoy.service.model.DriverStatusKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Service
@Slf4j
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private BusyCarMapper busyCarMapper;
    @Autowired
    private ApplyCarMapper applyCarMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BusyDriverMapper busyDriverMapper;

    @Override
    public boolean add(Car car) {
        int added = carMapper.insertSelective(car);
        return added > 0;
    }

    @Override
    public Car modify(Car car) {
        int moded = carMapper.updateByPrimaryKeySelective(car);
        if (moded > 0) return car;
        else return findById(car.getId());
    }

    @Override
    public List<Car> findByKind(int kind) {
        return carMapper.selectByKind(kind);
    }

    @Override
    public List<Car> findByCondition(int applyID) {
        ApplyCar apply = applyCarMapper.selectByPrimaryKey(applyID);
        CarCondition carCondition = new CarCondition();
        carCondition.setKind(apply.getCarKind());
        carCondition.setPeople(apply.getFollow().length());
        // 找出符合申请单条件的车
        List<Car> carBackup = carMapper.selectByCondition(carCondition);
        // 从符合条件的车中找出空闲车辆
        List<Car> cars = new ArrayList<>();
        Date start = apply.getStartTime(), end = apply.getEndTime();
        for (int i = 0; i < carBackup.size() || cars.size() < 5; i++) {
            CarStatusKey key = new CarStatusKey(carBackup.get(i).getId(), start, end);
            List<BusyCar> busyCars = busyCarMapper.selectBusy(key);
            if (busyCars == null || busyCars.size() == 0)
                cars.add(carBackup.get(i));
        }
        return cars;
    }

    @Override
    public Car findById(int id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer getStatus(Integer id) {
        Date now = new Date();
        log.info(now.toString());
        CarStatusKey key = new CarStatusKey(id, now, now);
        List<BusyCar> busyCars = busyCarMapper.selectStatusByPrimaryKey(key);
        if (busyCars.size() > 0)
            return 1;
        return 0;
    }

    @Override
    public PageInfo getAll(PageReq page) {
        PageHelper.startPage(page);
        List<Car> cars = carMapper.selectAll();
        return new PageInfo<>(cars);
    }

    @Override
    public int getCount() {
        return carMapper.getCount();
    }
}

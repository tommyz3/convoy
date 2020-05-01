package nju.software.convoy.service;

import com.github.pagehelper.PageInfo;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.data.entity.Car;
import nju.software.convoy.controller.RequestBody.CarCondition;

import java.util.List;

public interface CarService {
    boolean add(Car car);
    Car modify(Car car);
    List<Car> findByKind(int kind);
    List<Car> findByCondition(int applyID);
    Car findById(int id);
    Integer getStatus(Integer id);
    PageInfo getAll(PageReq page);
    int getCount();
}

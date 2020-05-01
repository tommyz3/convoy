package nju.software.convoy.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.RequestBody.CarReq;
import nju.software.convoy.controller.RequestBody.PageReq;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.data.entity.Car;
import nju.software.convoy.data.entity.User;
import nju.software.convoy.service.CarService;
import nju.software.convoy.controller.RequestBody.CarCondition;
import nju.software.convoy.service.DriverService;
import nju.software.convoy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Slf4j
@RestController("/car")
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    private DriverService driverService;

    // 添加车辆
    @RequestMapping("/add")
    public Result addCar(@Valid CarReq car, BindingResult bindingResult) {
        Car add = toEntity(car);
        boolean added = carService.add(add);
        if (added)
            return ResultFactory.success();
        return ResultFactory.failed();
    }

    // 获取推荐
    // 暂定接口，细节需要进一步修改
    @RequestMapping("/recommend")
    public Result recommend(@RequestParam(value = "applyID")int applyID) {
        List<Car> cars = carService.findByCondition(applyID);
        List<User> drivers = driverService.recommend(applyID);
        if (cars.isEmpty() || drivers.isEmpty()) {
            return ResultFactory.failed("没有复合条件的车辆和司机");
        }
        JSONObject json = new JSONObject();
        json.put("cars", cars);
        json.put("drivers", drivers);
        return ResultFactory.success(json);
    }

    /**
     * 获取车辆状态
     * @param id 车辆id
     * @return
     */
    @RequestMapping("/getStatus")
    public Result getStatus(Integer id){
        if (id == null)
            return ResultFactory.failed("参数错误");
        Integer size = carService.getStatus(id);
        return ResultFactory.success(size);
    }

    /**
     * 分页查找全部车辆
     * @param page 页数
     * @param size 每页大小
     * @return 当前页有数据
     */
    @RequestMapping("/getAll")
    public Result getAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        PageReq p = new PageReq(page, size);
        PageInfo cars = carService.getAll(p);
        return ResultFactory.success(cars);
    }

    // 请求参数转换为实体类
    private Car toEntity(CarReq r) {
        return new Car(0, r.getKind(), r.getNumberPlate(), r.getModel(), r.getPeople(),
                r.getColor(), r.getLoadWeight(), r.getFuel(), r.getServiceStart(), r.getInspect(),
                r.getInspectDdl(), r.getSafe(), r.getSafeDdl());
    }
}

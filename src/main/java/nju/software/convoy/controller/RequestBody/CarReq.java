package nju.software.convoy.controller.RequestBody;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Data
public class CarReq {
    private Integer kind;
    @NotBlank
    private String numberPlate;
    private String model;
    private Integer people;
    private String color;
    private Integer loadWeight;
    private Integer fuel;
    private Date serviceStart;
    private Date inspect;
    private Date inspectDdl;
    private Date safe;
    private Date safeDdl;
}

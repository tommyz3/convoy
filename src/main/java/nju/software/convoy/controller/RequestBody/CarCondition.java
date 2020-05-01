package nju.software.convoy.controller.RequestBody;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Data
public class CarCondition {
    @NotBlank
    private int kind;
    @NotBlank
    private int people;
}

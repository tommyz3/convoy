package nju.software.convoy.controller.RequestBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Data
public class ApplyReq {
    @NotNull(message = "车型不能为空")
    private Integer carKind;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @NotBlank(message = "上车地点不能为空")
    private String startPosition;
    private String endPosition;
    private String follow;
    private String reason;
    @NotBlank(message = "申请人不能为空")
    private String who;
    // 添加时为空
    private Integer car;
    private String driver;
}

package nju.software.convoy.controller.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: tommy_z
 * @Date: 2020/4/29
 */
@Data
@AllArgsConstructor
public class ApprovalReq {
    private String who;
    private String reason;
    private byte status;
    private Integer applyID;
}

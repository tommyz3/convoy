package nju.software.convoy.controller.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: tommy_z
 * @Date: 2020/4/28
 */
@Data
@AllArgsConstructor
public class PageReq {
    private int pageNum;
    private int pageSize;
}

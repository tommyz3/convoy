package nju.software.convoy.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: tommy_z
 * @Date: 2020/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorityKey {
    private String department;
    private int title;
}

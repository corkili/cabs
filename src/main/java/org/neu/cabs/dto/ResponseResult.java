package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结果
 * @author 李浩然
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {
    /**
     * 结果
     */
    private String result;

    /**
     * 消息
     */
    private String msg;
}

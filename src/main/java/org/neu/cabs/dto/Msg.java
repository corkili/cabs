package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息传递对象
 * @author 李浩然
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    private String title;
    private String content;
    private String extraInfo;
}

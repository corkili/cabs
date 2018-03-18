package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neu.cabs.constant.FlightState;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

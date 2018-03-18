package org.neu.cabs.dto;

import lombok.Data;
import org.neu.cabs.orm.Order;

/**
 * 订单项表单
 * @author 李浩然
 */
@Data
public class OrderItemForm {
    /**
     * 订单项ID
     */
    private Long id;

    /**
     * 航班费用
     */
    private Double flightCost;

    /**
     * 机建费用
     */
    private Double airportBuildCost;

    /**
     * 舱位类型
     */
    private String cabinType;

    /**
     * 航班号
     */
    private Long flightId;

    /**
     * 航班号
     */
    private String flightNumber;

    /**
     * 航班日期
     */
    private String flightDate;

    /**
     * 所属订单
     */
    private Long orderId;

    /**
     * 旅客ID
     */
    private Long passengerId;

    /**
     * 旅客姓名
     */
    private String passengerName;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号
     */
    private String certificateNumber;

    /**
     * 乘客生日
     */
    private String birthday;

    /**
     * 手机号
     */
    private String phone;



    public static Order to(OrderForm form) {
        return to(form, new Order());
    }

    public static Order to(OrderForm form, Order order) {
        return order;
    }
}

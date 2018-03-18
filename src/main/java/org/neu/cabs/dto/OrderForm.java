package org.neu.cabs.dto;

import lombok.Data;
import org.neu.cabs.constant.CabinType;
import org.neu.cabs.constant.CertificateType;
import org.neu.cabs.constant.OrderState;
import org.neu.cabs.orm.Order;
import org.neu.cabs.orm.OrderItem;
import org.neu.cabs.orm.Passenger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单表单
 * @author 李浩然
 */
@Data
public class OrderForm {
    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单流水号
     */
    private String serialNumber;

    /**
     * 订单总金额
     */
    private Double totalCost;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单状态
     */
    private String state;

    /**
     * 下单人ID
     */
    private Long userId;

    /**
     * 下单人用户名
     */
    private String username;

    /**
     * 订单条目
     */
    private List<OrderItemForm> orderItems;

    public static OrderForm from(Order order) {
        OrderForm form = new OrderForm();
        form.setId(order.getId());
        form.setSerialNumber(order.getSerialNumber());
        form.setTotalCost(order.getTotalCost());
        form.setCreateTime(order.getCreateTime());
        form.setState(order.getState().getState());
        form.setUserId(order.getBuyer().getId());
        form.setUsername(order.getBuyer().getUsername());
        List<OrderItemForm> orderItems = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemForm itemForm = new OrderItemForm();
            itemForm.setId(item.getId());
            itemForm.setFlightCost(item.getFlightCost());
            itemForm.setAirportBuildCost(item.getAirportBuildCost());
            itemForm.setCabinType(item.getCabinType().getCabin());
            itemForm.setFlightId(item.getFlight().getId());
            itemForm.setFlightNumber(item.getFlight().getFlightNumber());
            itemForm.setFlightDate(new SimpleDateFormat("yyyy-MM-dd").format(item.getFlight().getTakeoffDate()));
            itemForm.setPassengerId(item.getPassenger().getId());
            itemForm.setPassengerName(item.getPassenger().getPassengerName());
            itemForm.setCertificateType(item.getPassenger().getCertificateType().getTypeName());
            itemForm.setCertificateNumber(item.getPassenger().getCertificateNumber());
            itemForm.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(item.getPassenger().getBirthday()));
            itemForm.setPhone(item.getPassenger().getPhone());
            orderItems.add(itemForm);
        }
        form.setOrderItems(orderItems);
        return form;
    }

    public static Order to(OrderForm form) {
        return to(form, new Order());
    }

    public static Order to(OrderForm form, Order order) {
        order.setId(form.getId());
        order.setSerialNumber(form.getSerialNumber());
        order.setTotalCost(form.getTotalCost());
        order.setCreateTime(form.getCreateTime());
        order.setState(OrderState.valueOf(form.getState()));
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemForm itemForm : form.getOrderItems()) {
            OrderItem item = new OrderItem();
            item.setId(itemForm.getId());
            item.setFlightCost(itemForm.getFlightCost());
            item.setAirportBuildCost(itemForm.getAirportBuildCost());
            item.setCabinType(CabinType.valueOf(itemForm.getCabinType()));
            item.setOrder(order);
            Passenger passenger = new Passenger();
            passenger.setId(itemForm.getPassengerId());
            passenger.setPassengerName(itemForm.getPassengerName());
            passenger.setCertificateType(CertificateType.valueOf(itemForm.getCertificateType()));
            passenger.setCertificateNumber(itemForm.getCertificateNumber());
            try {
                passenger.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(itemForm.getBirthday()));
            } catch (ParseException e) {
                passenger.setBirthday(new Date());
            }
            passenger.setPhone(itemForm.getPhone());
            item.setPassenger(passenger);
        }
        return order;
    }
}

package org.neu.cabs.service;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Order;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

/**
 * 系统核心逻辑，订单服务接口
 * @author 李浩然
 */
public interface OrderService {

    /**
     * 获得所有订单
     * @return 订单列表
     */
    List<Order> getAllOrders();

    /**
     * 创建订单
     * @param order 订单实体
     * @return 服务结果，包含创建成功的订单(order: Order)
     */
    ServiceResult createOrder(Order order);

    /**
     * 支付订单
     * @param orderId 订单ID
     * @return 服务结果
     */
    ServiceResult payOrder(Long orderId);

    /**
     * 获取ID为orderId的订单
     * @param orderId 订单ID
     * @return 订单实体
     */
    Order getOrderById(Long orderId);

    /**
     * 获取流水号为orderSerialNumber的订单
     * @param orderSerialNumber 订单流水号
     * @return 订单实体
     */
    Order getOrderBySerialNumber(String orderSerialNumber);

    /**
     * 改签机票订单
     * @param order 订单实体
     * @return 服务结果
     */
    ServiceResult endorseOrder(Order order);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 服务结果
     */
    ServiceResult refundOrder(Long orderId);

    /**
     * 查找时间范围内的订单
     * @param start 开始时间
     * @param end 结束时间
     * @return 订单列表
     */
    List<Order> getOrderByDateRange(Date start, Date end);

    /**
     * 分页查找时间范围内的订单
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页参数
     * @return 订单列表
     */
    Page<Order> getOrderByDateRange(Date start, Date end, Pageable pageable);

    /**
     * 修改订单信息
     * @param order 订单实体
     * @return 服务结果
     */
    ServiceResult modifyOrder(Order order);
}

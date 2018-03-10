package org.neu.cabs.service;

import org.neu.cabs.orm.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，订单项（机票）服务接口
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public interface OrderItemService {
    /**
     * 根据乘客证件号搜索机票（游客可使用）
     * @param passengerCertificateNumber 乘客证件号
     * @return 订单项（机票）列表
     */
    List<OrderItem> searchOrderItemsByPassengerCertificateNumber(String passengerCertificateNumber);
}

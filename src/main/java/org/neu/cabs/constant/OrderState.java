package org.neu.cabs.constant;

/**
 * 订单状态
 * @author 李浩然
 */
public enum OrderState {
    /**
     * 未支付
     */
    UNPAID("未支付"),
    /**
     * 已支付
     */
    ALREADY_PAID("已支付"),
    /**
     * 已出票
     */
    ALREADY_OUT_OF_TICKET("已出票"),
    /**
     * 已退款
     */
    REFUNDED("已退款"),
    /**
     * 已取消
     */
    CANCELLED("已取消"),
    /**
     * 已完成
     */
    FINISHED("已完成");

    private String state;

    OrderState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

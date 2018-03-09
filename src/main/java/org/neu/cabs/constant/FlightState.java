package org.neu.cabs.constant;

/**
 * 航班状态
 * @author 李浩然
 */
public enum FlightState {
    /**
     * 计划
     */
    PLAN("计划"),
    /**
     * 正在登机
     */
    BOARDING("正在登机"),
    /**
     * 延误
     */
    DELAY("延误"),
    /**
     * 取消
     */
    CANCEL("取消"),
    /**
     * 到达
     */
    ARRIVE("到达");

    private String state;

    FlightState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

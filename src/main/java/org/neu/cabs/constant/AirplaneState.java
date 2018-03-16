package org.neu.cabs.constant;

/**
 * 飞机状态枚举类
 * @author 李浩然
 */
public enum AirplaneState {
    /**
     * 不可用
     */
    UNAVAILABLE("飞机当前不可用"),
    /**
     * 停靠机场
     */
    STOP_AT_AIRPORT("飞机当前停靠机场"),
    /**
     * 正在飞行
     */
    FLYING("飞机正在飞行"),
    /**
     * 无
     */
    NONE("无");

    private String state;

    AirplaneState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

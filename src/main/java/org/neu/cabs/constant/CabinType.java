package org.neu.cabs.constant;

/**
 * 舱位类型
 * @author 李浩然
 */
public enum CabinType {
    /**
     * 商务舱
     */
    BUSINESS("商务舱"),
    /**
     * 头等舱
     */
    FISRT("头等舱"),
    /**
     * 经济舱
     */
    ECONOMY("经济舱");

    private String cabin;

    CabinType(String cabin) {
        this.cabin = cabin;
    }

    public String getCabin() {
        return cabin;
    }
}

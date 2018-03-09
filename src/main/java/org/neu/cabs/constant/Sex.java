package org.neu.cabs.constant;

/**
 * 性别
 * @author 李浩然
 */
public enum Sex {
    /**
     * 男
     */
    MAN("男"),
    /**
     * 女
     */
    WOMAN("女");

    private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}

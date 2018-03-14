package org.neu.cabs.constant;

/**
 * 证件类型
 * @author 李浩然
 */
public enum CertificateType {
    /**
     * 中华人民共和国居民身份证
     */
    CHINA_IDENTITY_CARD("中华人民共和国居民身份证"),
    /**
     * 护照
     */
    PASSPORT("护照"),
    /**
     * 军人证
     */
    MILITARY_CARD("军人证"),
    /**
     * 回乡证
     */
    HVPS("回乡证"),
    /**
     * 台胞证
     */
    MTP("台胞证"),
    /**
     * 户口簿
     */
    BOOKLET("户口簿"),
    /**
     * 出生证明
     */
    BIRTH_CERTIFICATE("出生证明"),
    /**
     * 外国人永久居留身份证
     */
    FOREIGNERS_ID_CARD("外国人永久居留身份证"),
    /**
     * 其他
     */
    OTHER("其他");

    private String name;

    CertificateType(String name) {
        this.name = name;
    }
}

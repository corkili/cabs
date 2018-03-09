package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 地址组件
 * @author 李浩然
 */
@Embeddable
@Data
@NoArgsConstructor
public class Address {

    /**
     * 省份
     */
    @AttributeOverrides({
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "province")
            )
    })
    private Province province;

    /**
     * 城市
     */
    @AttributeOverrides({
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "city")
            )
    })
    private City city;

    /**
     * 区县
     */
    @AttributeOverrides({
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "county")
            )
    })
    private County county;

    /**
     * 构造函数
     * @param province 省份名称
     * @param city 城市名称
     * @param county 区县名称
     */
    public Address(String province, String city, String county) {
        this.province = new Province(province);
        this.city = new City(city);
        this.county = new County(county);
    }
}


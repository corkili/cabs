package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 城市组件类
 * @author 李浩然
 */
@Embeddable
@Data
@NoArgsConstructor
public class City {
    /**
     * 城市名称
     */
    @NotNull
    @Column
    private String name;

    /**
     * 城市下辖区县
     */
    @Transient
    private Set<County> counties;

    /**
     * 构造函数
     * @param name 城市名称
     */
    public City(String name) {
        this.name = name;
    }

}

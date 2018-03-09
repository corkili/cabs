package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * 区县组件类
 * @author 李浩然
 */
@Embeddable
@Data
@NoArgsConstructor
public class County {

    /**
     * 区县名称
     */
    @NotNull
    @Column
    private String name;

    /**
     * 构造函数
     * @param name 区县名称
     */
    public County(String name) {
        this.name = name;
    }

}

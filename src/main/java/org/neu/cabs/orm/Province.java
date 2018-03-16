package org.neu.cabs.orm;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 省份组件类
 * @author 李浩然
 */
@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Province {

    /**
     * 省份名称
     */
    @NotNull
    @Column
    private String name;

    /**
     * 所包含的城市
     */
    @Transient
    private Set<City> cities;

    public Province(String name) {
        this.name = name;
    }

}

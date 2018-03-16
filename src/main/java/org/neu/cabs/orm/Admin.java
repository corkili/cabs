package org.neu.cabs.orm;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 管理员用户实体类
 * @author 李浩然
 * @see BaseUser
 */
@Entity
@DiscriminatorValue("admin")
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Admin extends BaseUser {
    /**
     * 是否是超级管理员
     */
    @NotNull
    private boolean superManager;

}

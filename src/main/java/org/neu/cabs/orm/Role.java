package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 系统角色实体类
 * @author 李浩然
 */
@Entity
@Table(name = "ROLE")
@Data
@NoArgsConstructor
public class Role {

    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称，以"ROLE_"开头，全大写字母
     */
    @Column(unique = true, length = 20)
    private String name;

    /**
     * 构造函数
     * @param name 角色名称
     */
    public Role(String name) {
        this.name = name;
    }
}

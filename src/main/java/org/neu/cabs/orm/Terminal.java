package org.neu.cabs.orm;

import lombok.Data;

import javax.persistence.*;

/**
 * 航站楼实体
 * @author 李浩然
 */
@Entity
@Table(name = "TERMINAL")
@Data
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String terminalName;

}

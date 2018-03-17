package org.neu.cabs.orm;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 航站楼实体
 * @author 李浩然
 */
@Entity
@Table(name = "TERMINAL")
@Setter
@Getter
@EqualsAndHashCode
public class Terminal implements Comparable<Terminal> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String terminalName;


    @Override
    public int compareTo(Terminal o) {
        return this.terminalName.compareTo(o.terminalName);
    }
}

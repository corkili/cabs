package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neu.cabs.constant.AirplaneState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 飞机实体类
 * @author 李浩然
 */
@Entity
@Table(name = "AIRPLANE")
@Data
@NoArgsConstructor
public class Airplane {
    /**
     * 飞机Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 飞机名称
     */
    @Column(length = 50)
    @NotNull
    private String planeName;

    /**
     * 座位数
     */
    @Column
    @NotNull
    private Integer seatNumber;

    /**
     * 飞机状态
     */
    @Column
    @NotNull
    @Enumerated
    private AirplaneState state;

    /**
     * 所属公司
     */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "COMPANY_ID")
    @NotNull
    private AirlineCompany belongToCompany;

    /**
     * 当前停靠机场
     */
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CURRENT_AIRPORT_ID")
    @NotNull
    private Airport currentAirport;

    /**
     * 待执飞航班
     */
    @OneToMany(mappedBy = "airplane", cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private Set<Flight> flights;

}

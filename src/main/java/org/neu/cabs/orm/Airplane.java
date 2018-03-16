package org.neu.cabs.orm;

import lombok.*;
import org.hibernate.annotations.FetchMode;
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
@Setter
@Getter
@NoArgsConstructor
public class Airplane {
    /**
     * 飞机Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 飞机编号（唯一）
     */
    @Column(length = 20)
    @NotNull
    private String airplaneSerialNumber;

    /**
     * 飞机名称
     */
    @Column(length = 50)
    @NotNull
    private String airplaneName;

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
    @Enumerated(EnumType.STRING)
    private AirplaneState state;

    /**
     * 所属公司
     */
    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    @NotNull
    private AirlineCompany belongToCompany;

    /**
     * 当前停靠机场
     */
    @ManyToOne
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "CURRENT_AIRPORT_ID")
    private Airport currentAirport;

    /**
     * 待执飞航班
     */
    @OneToMany(mappedBy = "airplane", cascade = { CascadeType.MERGE })
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Set<Flight> flights;

}

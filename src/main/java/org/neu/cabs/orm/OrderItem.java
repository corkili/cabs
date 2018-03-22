package org.neu.cabs.orm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.neu.cabs.constant.CabinType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 订单项实体类
 * @author 李浩然
 */
@Entity
@Table(name = "ORDER_ITEM")
@Setter
@Getter
@NoArgsConstructor
public class OrderItem {

    /**
     * 订单项ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 航班费用
     */
    @Column(scale = 2)
    @NotNull
    private Double flightCost;

    /**
     * 机建费用
     */
    @Column(scale = 2)
    @NotNull
    private Double airportBuildCost;

    /**
     * 舱位类型
     */
    @Column
    @NotNull
    @Enumerated
    private CabinType cabinType;

    /**
     * 航班
     */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "FLIGHT_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Flight flight;

    /**
     * 所属订单
     */
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Order order;

    /**
     * 乘客
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "PASSENGER_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Passenger passenger;

    public Double getCost() {
        return flightCost + airportBuildCost;
    }
}

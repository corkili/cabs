package org.neu.cabs.orm;

import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.neu.cabs.constant.OrderState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * 订单实体类
 * @author 李浩然
 */
@Entity
@Table(name = "ORDERS")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Order {
    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单流水号
     */
    @Column(unique = true)
    @NotNull
    private String serialNumber;

    /**
     * 订单总金额
     */
    @Column(scale = 2)
    @NotNull
    private Double totalCost;

    /**
     * 订单创建时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createTime;

    /**
     * 订单状态
     */
    @Column
    @NotNull
    @Enumerated
    private OrderState state;

    /**
     * 订单条目
     */
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "order")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Set<OrderItem> orderItems;

    /**
     * 下单人
     */
    @ManyToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name = "BUYER_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private User buyer;
}

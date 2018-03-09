package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
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
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "order")
    private Set<OrderItem> orderItems;

    /**
     * 下单人
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "BUYER_ID")
    private User buyer;
}

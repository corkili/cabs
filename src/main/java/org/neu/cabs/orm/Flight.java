package org.neu.cabs.orm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.neu.cabs.constant.FlightState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 航班实体类
 * @author 李浩然
 */
@Entity
@Table(name = "FLIGHT")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Flight {
    /**
     * 航班ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 航班号
     */
    @Column
    @NotNull
    private String flightNumber;

    /**
     * 起飞日期
     */
    @Column
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date takeoffDate;

    /**
     * 计划起飞时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date plannedTakeoffTime;

    /**
     * 计划到达时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date plannedArrivalTime;

    /**
     * 实际起飞时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualTakeoffTime;

    /**
     * 实际到达时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualArrivalTime;

    /**
     * 商务舱座位数
     */
    @Column
    @NotNull
    private Integer businessCabinSeatNumber;

    /**
     * 头等舱座位数
     */
    @Column
    @NotNull
    private Integer firstCabinSeatNumber;

    /**
     * 经济舱座位数
     */
    @Column
    @NotNull
    private Integer economyCabinSeatNumber;

    /**
     * 商务舱价格
     */
    @Column(scale = 2)
    @NotNull
    private Double businessCabinPrice;

    /**
     * 头等舱价格
     */
    @Column(scale = 2)
    @NotNull
    private Double firstCabinPrice;

    /**
     * 经济舱价格
     */
    @Column(scale = 2)
    @NotNull
    private Double economyCabinPrice;

    /**
     * 商务舱折扣
     */
    @Column(scale = 2)
    @NotNull
    private Double businessCabinDiscount;

    /**
     * 头等舱折扣
     */
    @Column(scale = 2)
    @NotNull
    private Double firstCabinDiscount;

    /**
     * 经济舱折扣
     */
    @Column(scale = 2)
    @NotNull
    private Double economyCabinDiscount;

    /**
     * 航班状态
     */
    @Column
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private FlightState state;

    /**
     * 所属航空公司
     */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "COMPANY_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private AirlineCompany airlineCompany;

    /**
     * 执飞飞机
     */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "AIRPLANE_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Airplane airplane;

    /**
     * 出发城市
     */

    @AttributeOverrides(value = {
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "DEPARTURE_CITY"))
    })
    private City departureCity;

    /**
     * 到达城市
     */

    @AttributeOverrides(value = {
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "ARRIVAL_CITY"))
    })
    private City arrivalCity;

    /**
     * 出发机场
     */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "DEPARTURE_AIRPORT_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Airport departureAirport;

    /**
     * 到达机场
     */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "ARRIVAL_AIRPORT_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Airport arrivalAirport;

    /**
     * 出发航站楼
     */
    @ManyToOne
    @JoinColumn(name = "DEPARTURE_TERMINAL_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Terminal departureTerminal;

    /**
     * 到达航站楼
     */
    @ManyToOne
    @JoinColumn(name = "ARRIVAL_TERMINAL_ID")
    @org.hibernate.annotations.Fetch(value = FetchMode.JOIN)
    private Terminal arrivalTerminal;
}

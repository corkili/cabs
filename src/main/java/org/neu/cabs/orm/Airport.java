package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 机场实体类
 * @author 李浩然
 */
@Entity
@Table(name = "AIRPORT")
@Data
@NoArgsConstructor
public class Airport {
    /**
     * 机场ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 中文名
     */
    @Column(length = 50)
    @NotNull
    private String chineseName;

    /**
     * 英文名
     */
    @Column(length = 100)
    @NotNull
    private String englishName;

    /**
     * 机场IATA代码
     */
    @Column(length = 5)
    @NotNull
    private String iataCode;

    /**
     * 机场ICAO代码
     */
    @Column(length = 5)
    @NotNull
    private String icaoCode;

    /**
     * 飞行区等级
     */
    @Column(length = 3)
    @NotNull
    private String flightLevel;

    /**
     * 机位数
     */
    @Column
    @NotNull
    private Integer seatOfPlane;

    /**
     * 机场地址
     */
    @NotNull
    private Address address;
}

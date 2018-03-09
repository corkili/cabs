package org.neu.cabs.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * 航空公司实体类
 * @author 李浩然
 */
@Entity
@Table(name = "AIRLINE_COMPANY")
@Data
@NoArgsConstructor
public class AirlineCompany {

    /**
     * 公司ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司名称
     */
    @Column(length = 50)
    @NotNull
    private String companyName;

    /**
     * 公司代码
     */
    @Column(length = 3)
    @NotNull
    private String companyCode;

    /**
     * 成立时间
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date establishTime;

    /**
     * 拥有飞机
     */
    @OneToMany(mappedBy = "belongToCompany", fetch = FetchType.EAGER,
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    private Set<Airplane> airplanes;


}

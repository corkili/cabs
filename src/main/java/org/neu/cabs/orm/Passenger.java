package org.neu.cabs.orm;

import lombok.*;
import org.neu.cabs.constant.CertificateType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 旅客实体类
 * @author 李浩然
 */
@Entity
@Table(name = "PASSENGER")
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    /**
     * 旅客ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 旅客姓名
     */
    @Column(length = 50)
    private String passengerName;

    /**
     * 证件类型
     */
    @Column
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;

    /**
     * 证件号
     */
    @Column(length = 30)
    @NotNull
    private String certificateNumber;

    /**
     * 生日
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * 手机号
     */
    @Column(length = 20)
    private String phone;
}

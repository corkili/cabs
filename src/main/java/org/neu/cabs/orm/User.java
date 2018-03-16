package org.neu.cabs.orm;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.neu.cabs.constant.CertificateType;
import org.neu.cabs.constant.Sex;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * 用户实体类
 * @author 李浩然
 * @see org.neu.cabs.orm.BaseUser
 */
@Entity
@DiscriminatorValue("user")
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends BaseUser {

    /**
     * 真实姓名
     */
    @Column(length = 50)
    @NotNull
    private String realName;

    /**
     * 证件类型
     */
    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private CertificateType certificateType;

    /**
     * 证件号
     */
    @Column(length = 30)
    @NotNull
    private String certificateNumber;

    /**
     * 性别
     */
    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sex sex;

    /**
     * 生日
     */
    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * 手机号
     */
    @Column(length = 20)
    @NotNull
    private String phone;

    /**
     * 邮箱
     */
    @Column
    @NotNull
    @Email
    private String email;

    /**
     * 地址
     */
    @NotNull
    private Address address;

    /**
     * 验证码发送时间
     */
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date verificationTime;

    /**
     * 验证码
     */
    @Column
    private String verificationCode;

    /**
     * 所有订单
     */
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER,
            cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private Set<Order> orders;

    /**
     * 常用旅客
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private Set<Passenger> passengers;

}

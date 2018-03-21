package org.neu.cabs.dto;

import lombok.Data;
import org.neu.cabs.constant.CertificateType;
import org.neu.cabs.orm.Passenger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 旅客表单
 * @author 李浩然
 */
@Data
public class PassengerForm {
    /**
     * 旅客ID
     */
    private Long id;

    /**
     * 旅客姓名
     */
    private String name;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号
     */
    private String certificateNumber;

    /**
     * 生日
     */
    private String birthday;

    public static PassengerForm from(Passenger passenger) {
        PassengerForm form = new PassengerForm();
        form.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(passenger.getBirthday()));
        form.setId(passenger.getId());
        form.setCertificateType(passenger.getCertificateType().getTypeName());
        form.setCertificateNumber(passenger.getCertificateNumber());
        form.setName(passenger.getPassengerName());
        return form;
    }

    public static Passenger to(PassengerForm form, Passenger passenger) {
        try {
            passenger.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(form.getBirthday()));
        } catch (ParseException e) {
            passenger.setBirthday(new Date());
        }
        passenger.setId(form.getId());
        passenger.setCertificateType(CertificateType.valueOf(form.getCertificateType()));
        passenger.setCertificateNumber(form.getCertificateNumber());
        passenger.setPassengerName(form.getName());
        return passenger;
    }

    public static Passenger to(PassengerForm form) {
        return to(form, new Passenger());
    }
}

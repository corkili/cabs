package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neu.cabs.orm.Airplane;
import org.neu.cabs.orm.Flight;

import java.util.HashSet;
import java.util.Set;

/**
 * 飞机表单
 * @author 李浩然
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneForm {
    /**
     * 飞机Id
     */
    private Long id;

    /**
     * 飞机编号，唯一
     */
    private String airplaneSerialNumber;

    /**
     * 飞机名称
     */
    private String airplaneName;

    /**
     * 座位数
     */
    private Integer seatNumber;

    /**
     * 飞机状态
     */
    private String state;

    /**
     * 所属公司ID
     */
    private Integer belongToCompany;

    /**
     * 所属公司名称
     */
    private String belongToCompanyName;

    /**
     * 当前停靠机场ID
     */
    private Integer currentAirport;

    /**
     * 当前停靠机场名称
     */
    private String currentAirportName;

    /**
     * 航班号集合
     */
    private Set<String> flightNumbers;

    public static AirplaneForm from(Airplane airplane) {
        if (airplane == null) {
            return null;
        }
        AirplaneForm airplaneForm = new AirplaneForm();
        airplaneForm.setId(airplane.getId());
        airplaneForm.setAirplaneSerialNumber(airplane.getAirplaneSerialNumber());
        airplaneForm.setAirplaneName(airplane.getAirplaneName());
        airplaneForm.setSeatNumber(airplane.getSeatNumber());
        airplaneForm.setState(airplane.getState().getState());
        airplaneForm.setBelongToCompany(airplane.getBelongToCompany().getId());
        airplaneForm.setBelongToCompanyName(airplane.getBelongToCompany().getCompanyName() + "("
                + airplane.getBelongToCompany().getCompanyCode() + ")");

        if (airplane.getCurrentAirport() == null) {
            airplaneForm.setCurrentAirport(-1);
            airplaneForm.setCurrentAirportName("无");
        } else {
            airplaneForm.setCurrentAirport(airplane.getCurrentAirport().getId());
            airplaneForm.setCurrentAirportName(airplane.getCurrentAirport().getChineseName()
                    + "(" + airplane.getCurrentAirport().getIataCode() + ")");
        }

        Set<String> flightNumbers = new HashSet<>();
        for (Flight flight : airplane.getFlights()) {
            flightNumbers.add(flight.getFlightNumber());
        }
        airplaneForm.setFlightNumbers(flightNumbers);
        return airplaneForm;
    }

}

package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.orm.Terminal;

import java.util.*;

/**
 * 机场表单
 * @author 李浩然
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportForm {
    /**
     * 机场ID
     */
    private Integer id;

    /**
     * 中文名
     */
    private String chineseName;

    /**
     * 英文名
     */
    private String englishName;

    /**
     * 机场IATA代码
     */
    private String iataCode;

    /**
     * 机场ICAO代码
     */
    private String icaoCode;

    /**
     * 飞行区等级
     */
    private String flightLevel;

    /**
     * 机位数
     */
    private Integer seatOfPlane;

    /**
     * 机场地址-省份
     */
    private String province;

    /**
     * 机场地址-城市
     */
    private String city;

    /**
     * 航站楼字符串表示
     */
    private String terminalString;

    /**
     * 航站楼数量
     */
    private Integer terminalNumber;

    public static AirportForm from(Airport airport) {
        AirportForm airportForm = new AirportForm();
        airportForm.setId(airport.getId());
        airportForm.setChineseName(airport.getChineseName());
        airportForm.setEnglishName(airport.getEnglishName());
        airportForm.setIataCode(airport.getIataCode());
        airportForm.setIcaoCode(airport.getIcaoCode());
        airportForm.setFlightLevel(airport.getFlightLevel());
        airportForm.setSeatOfPlane(airport.getSeatOfPlane());
        airportForm.setProvince(airport.getAddress().getProvince().getName());
        airportForm.setCity(airport.getAddress().getCity().getName());
        airportForm.setTerminalString(airport.getTerminalString());
        airportForm.setTerminalNumber(airport.getTerminals().size());
        return airportForm;
    }

    public static Airport to(AirportForm form) {
        Airport airport = new Airport();
        airport.setChineseName(form.getChineseName());
        airport.setEnglishName(form.getEnglishName());
        airport.setIataCode(form.getIataCode().toUpperCase());
        airport.setIcaoCode(form.getIcaoCode().toUpperCase());
        airport.setFlightLevel(form.getFlightLevel());
        airport.setSeatOfPlane(form.getSeatOfPlane());
        airport.setAddress(new Address(form.province, form.city, ""));
        Set<Terminal> terminals = new HashSet<>();
        for (int i = 1; i <= form.terminalNumber; i++) {
            Terminal terminal = new Terminal();
            terminal.setTerminalName("T" + i);
            terminals.add(terminal);
        }
        airport.setTerminals(terminals);
        return airport;
    }

    public static Airport to(AirportForm form, Airport airport) {
        airport.setChineseName(form.getChineseName());
        airport.setEnglishName(form.getEnglishName());
        airport.setIataCode(form.getIataCode().toUpperCase());
        airport.setIcaoCode(form.getIcaoCode().toUpperCase());
        airport.setFlightLevel(form.getFlightLevel());
        airport.setSeatOfPlane(form.getSeatOfPlane());
        airport.setAddress(new Address(form.province, form.city, ""));
        List<Terminal> terminalList = new ArrayList<>(airport.getTerminals());
        Set<Terminal> terminals = new HashSet<>();
        Collections.sort(terminalList);
        if (terminalList.size() < form.terminalNumber) {
            for (int i = terminalList.size() + 1; i <= form.terminalNumber; i++) {
                Terminal terminal = new Terminal();
                terminal.setTerminalName("T" + i);
                terminalList.add(terminal);
            }
            terminals.addAll(terminalList);
        } else {
            for (int i = 0; i < form.terminalNumber; i++) {
                terminals.add(terminalList.get(i));
            }
        }
        airport.setTerminals(terminals);
        return airport;
    }
}

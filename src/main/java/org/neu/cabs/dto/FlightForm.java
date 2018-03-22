package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neu.cabs.constant.FlightState;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 航班表单
 * @author 李浩然
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightForm {
    /**
     * *航班ID
     */
    private Long id;

    /**
     * *航班号
     */
    private String flightNumber;

    /**
     * *起飞日期：yyyy-MM-dd
     */
    private String takeoffDate;

    /**
     * *计划起飞时间：HH:mm
     */
    private String plannedTakeoffTime;

    /**
     * *预计飞行时长：小时:分钟
     */
    private String voyage;

    /**
     * 计划到达时间：yyyy-MM-dd HH:mm
     */
    private String plannedArrivalTime;

    /**
     * 实际起飞时间
     */
    private String actualTakeoffTime;

    /**
     * 实际到达时间
     */
    private String actualArrivalTime;

    /**
     * *商务舱座位数
     */
    private Integer businessCabinSeatNumber;

    /**
     * *头等舱座位数
     */
    private Integer firstCabinSeatNumber;

    /**
     * *经济舱座位数
     */
    private Integer economyCabinSeatNumber;

    /**
     * *商务舱价格
     */
    private Double businessCabinPrice;

    /**
     * *头等舱价格
     */
    private Double firstCabinPrice;

    /**
     * *经济舱价格
     */
    private Double economyCabinPrice;

    /**
     * *商务舱折扣
     */
    private Double businessCabinDiscount;

    /**
     * *头等舱折扣
     */
    private Double firstCabinDiscount;

    /**
     * *经济舱折扣
     */
    private Double economyCabinDiscount;

    /**
     * *航班状态：枚举常量名
     */
    private String state;

    /**
     * 航班状态：state.getState();
     */
    private String stateStr;

    /**
     * *所属航空公司ID
     */
    private Integer airlineCompany;

    /**
     * 所属航空公司：名称(公司代码)
     */
    private String airlineCompanyStr;

    /**
     * *执飞飞机ID
     */
    private Long airplane;

    /**
     * 执飞飞机：[公司代码]飞机编号(飞机名称)
     */
    private String airplaneStr;

    /**
     * *出发省份
     */
    private String departureProvince;

    /**
     * *出发城市
     */
    private String departureCity;

    /**
     * *到达城市
     */
    private String arrivalProvince;

    /**
     * *到达城市
     */
    private String arrivalCity;

    /**
     * *出发机场：机场ID_航站楼名称
     */
    private String departureAirport;

    /**
     * 出发机场：IATA-机场中文名
     */
    private String departureAirportStr;

    /**
     * *到达机场：机场ID_航站楼名称
     */
    private String arrivalAirport;

    /**
     * 到达机场：IATA-机场中文名
     */
    private String arrivalAirportStr;

    /**
     * 出发航站楼
     */
    private String departureTerminal;

    /**
     * 到达航站楼
     */
    private String arrivalTerminal;

    public static FlightForm from(Flight flight) {
        FlightForm form = new FlightForm();
        form.setId(flight.getId());
        form.setFlightNumber(flight.getFlightNumber());
        form.setTakeoffDate(new SimpleDateFormat("yyyy-MM-dd").format(flight.getTakeoffDate()));
        form.setPlannedTakeoffTime(new SimpleDateFormat("HH:mm").format(flight.getPlannedTakeoffTime()));
        long diff = (flight.getPlannedArrivalTime().getTime() - flight.getPlannedTakeoffTime().getTime()) / 1000;
        form.setVoyage(diff / 3600 + ":" + diff / 60 % 60);
        form.setPlannedArrivalTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(flight.getPlannedArrivalTime()));
        form.setBusinessCabinSeatNumber(flight.getBusinessCabinSeatNumber());
        form.setFirstCabinSeatNumber(flight.getFirstCabinSeatNumber());
        form.setEconomyCabinSeatNumber(flight.getEconomyCabinSeatNumber());
        form.setBusinessCabinPrice(flight.getBusinessCabinPrice());
        form.setFirstCabinPrice(flight.getFirstCabinPrice());
        form.setEconomyCabinPrice(flight.getEconomyCabinPrice());
        form.setBusinessCabinDiscount(flight.getBusinessCabinDiscount());
        form.setFirstCabinDiscount(flight.getFirstCabinDiscount());
        form.setEconomyCabinDiscount(flight.getEconomyCabinDiscount());
        form.setState(flight.getState().name());
        form.setStateStr(flight.getState().getState());
        form.setAirlineCompany(flight.getAirlineCompany().getId());
        form.setAirlineCompanyStr(flight.getAirlineCompany().getCompanyName()
                + "(" + flight.getAirlineCompany().getCompanyCode() + ")");
        if (flight.getAirplane() != null) {
            form.setAirplane(flight.getAirplane().getId());
            form.setAirplaneStr("[" + flight.getAirplane().getBelongToCompany().getCompanyCode() + "]"
                    + flight.getAirplane().getAirplaneSerialNumber() + "(" + flight.getAirplane().getAirplaneName() + ")");
        } else {
            form.setAirplane(null);
            form.setAirplaneStr("无");
        }
        form.setDepartureProvince(flight.getDepartureAddress().getProvince().getName());
        form.setDepartureCity(flight.getDepartureAddress().getCity().getName());
        form.setArrivalProvince(flight.getArrivalAddress().getProvince().getName());
        form.setArrivalCity(flight.getArrivalAddress().getCity().getName());
        form.setDepartureAirport(flight.getDepartureAirport().getId() + "_" + flight.getDepartureTerminal().getTerminalName());
        form.setDepartureAirportStr(flight.getDepartureAirport().getIataCode() + "-"
                + flight.getDepartureAirport().getChineseName());
        form.setArrivalAirport(flight.getArrivalAirport().getId() + "_" + flight.getArrivalTerminal().getTerminalName());
        form.setArrivalAirportStr(flight.getArrivalAirport().getIataCode() + "-"
                + flight.getArrivalAirport().getChineseName());
        form.setDepartureTerminal(flight.getDepartureTerminal().getTerminalName());
        form.setArrivalTerminal(flight.getArrivalTerminal().getTerminalName());
        return form;
    }

    public static Flight to(FlightForm form) {
        return to(form, new Flight());
    }

    public static Flight to(FlightForm form, Flight flight) {
        flight.setId(form.getId());
        flight.setFlightNumber(form.getFlightNumber());
        try {
            flight.setTakeoffDate(new SimpleDateFormat("yyyy-MM-dd").parse(form.getTakeoffDate()));
        } catch (ParseException e) {
            flight.setTakeoffDate(new Date());
        }
        try {
            flight.setPlannedTakeoffTime(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .parse(form.getTakeoffDate() + " " + form.getPlannedTakeoffTime()));
        } catch (ParseException e) {
            flight.setTakeoffDate(new Date());
        }
        String[] time = form.getVoyage().split(":");
        flight.setPlannedArrivalTime(new Date(
                flight.getPlannedTakeoffTime().getTime() + Long.valueOf(time[0]) * 3600000
                        + Long.valueOf(time[1]) * 60000));
        flight.setBusinessCabinSeatNumber(form.getBusinessCabinSeatNumber());
        flight.setFirstCabinSeatNumber(form.getFirstCabinSeatNumber());
        flight.setEconomyCabinSeatNumber(form.getEconomyCabinSeatNumber());
        flight.setBusinessCabinPrice(form.getBusinessCabinPrice());
        flight.setFirstCabinPrice(form.getFirstCabinPrice());
        flight.setEconomyCabinPrice(form.getEconomyCabinPrice());
        flight.setBusinessCabinDiscount(form.getBusinessCabinDiscount());
        flight.setFirstCabinDiscount(form.getFirstCabinDiscount());
        flight.setEconomyCabinDiscount(form.getEconomyCabinDiscount());
        flight.setState(FlightState.valueOf(form.getState()));
        flight.setDepartureAddress(new Address(form.getDepartureProvince(), form.getDepartureCity(), ""));
        flight.setArrivalAddress(new Address(form.getArrivalProvince(), form.getArrivalCity(), ""));
        return flight;
    }
}

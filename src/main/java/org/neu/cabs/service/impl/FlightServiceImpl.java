package org.neu.cabs.service.impl;

import org.neu.cabs.dao.FlightRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.*;
import org.neu.cabs.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统核心逻辑，航班服务接口实现类
 * @author 李浩然 谭湖东
 * @see org.neu.cabs.service.FlightService
 */
@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> searchFlight(Address from, Address to, Date date) {

        return flightRepository.findAllByDepartureAddress_ProvinceAndDepartureAddress_CityAndArrivalAddress_ProvinceAndArrivalAddress_CityAndTakeoffDate(
                from.getProvince(), from.getCity(), to.getProvince(), to.getCity(), date);
    }

    @Override
    public List<Flight> searchFlight(Airport from, Airport to, Date date) {

        return flightRepository.findAllByDepartureAirportAndArrivalAirportAndTakeoffDate(from,to,date);
    }

    @Override
    public Flight searchFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    @Override
    public ServiceResult createFlight(Flight flight) {
        ServiceResult serviceResult;
        Flight savedFlight = flightRepository.save(flight);
        if (savedFlight.getId() != null){
            serviceResult = new ServiceResult(true,"航班创建成功！", "flight", savedFlight);
        }else {
            serviceResult = new ServiceResult(false,"航班创建失败！");
        }

        return serviceResult;
    }

    @Override
    public List<Flight> getAllFlight() {

        return flightRepository.findAll();
    }

    @Override
    public Page<Flight> getFlights(Pageable pageable) {

        return flightRepository.findAll(pageable);
    }

    @Override
    public List<Flight> getFlights(Date start, Date end) {

        return flightRepository.findAllByTakeoffDateBetween(start,end);
    }

    @Override
    public Page<Flight> getFlights(Date start, Date end, Pageable pageable) {

        return flightRepository.findAllByTakeoffDateBetween(start,end,pageable);
    }

    @Override
    public List<Flight> getFlights(Date start, Date end, AirlineCompany airlineCompany) {

        return flightRepository.findAllByTakeoffDateBetweenAndAirlineCompany(start,end,airlineCompany);
    }

    @Override
    public Page<Flight> getFlights(Date start, Date end, AirlineCompany airlineCompany, Pageable pageable) {
        return flightRepository.findAllByTakeoffDateBetweenAndAirlineCompany(start,end,airlineCompany,pageable);
    }

    @Override
    public List<Flight> getFlights(Date start, Date end, Airport departureAirport, Airport arrivalAirport) {
        return flightRepository.findAllByTakeoffDateBetweenAndDepartureAirportAndArrivalAirport(start,end,departureAirport,arrivalAirport);
    }

    @Override
    public Page<Flight> getFlights(Date start, Date end, Airport departureAirport, Airport arrivalAirport, Pageable pageable) {
        return flightRepository.findAllByTakeoffDateBetweenAndDepartureAirportAndArrivalAirport(start,end,departureAirport,arrivalAirport,pageable);
    }

    @Override
    public Flight getFlightById(Long id) {

        return flightRepository.findOne(id);
    }

    @Override
    public ServiceResult modifyFlight(Flight flight) {
        ServiceResult serviceResult;
        Flight modifiedFlight = flightRepository.save(flight);
        if (modifiedFlight != null){
            serviceResult = new ServiceResult(true,"航班修改成功！", "flight", modifiedFlight);
        }else {
            serviceResult = new ServiceResult(false,"航班修改失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult deleteFlightById(Long id) {
        ServiceResult serviceResult;
        flightRepository.delete(id);
        if (null == flightRepository.findOne(id)){
            serviceResult = new ServiceResult(true,"航班删除成功！");
        }else {
            serviceResult = new ServiceResult(false,"航班删除失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult batchCreateFlight(List<Flight> flights) {
        ServiceResult serviceResult;
        List<Flight> flightsT = flightRepository.save(flights);
        if (flightsT.isEmpty()){
            serviceResult = new ServiceResult(false,"所有航班信息均建立失败！");
        }else if(flightsT.size()<flights.size()){
            serviceResult = new ServiceResult(true,"部分航班的信息创建成功！");
        }else {
            serviceResult = new ServiceResult(true,"所有航班的信息均建立成功！");
        }
        return serviceResult;
    }
}

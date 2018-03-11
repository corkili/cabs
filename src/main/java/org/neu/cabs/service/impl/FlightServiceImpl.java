package org.neu.cabs.service.impl;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.orm.City;
import org.neu.cabs.orm.Flight;
import org.neu.cabs.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统核心逻辑，航班服务接口实现类
 * @author 李浩然
 * @see org.neu.cabs.service.FlightService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlightServiceImpl implements FlightService {

    @Override
    public List<Flight> searchFlight(City from, City to, Date date) {
        return null;
    }

    @Override
    public List<Flight> searchFlight(Airport from, Airport to, Date date) {
        return null;
    }

    @Override
    public Flight searchFlightByFlightNumber(String flightNumber) {
        return null;
    }

    @Override
    public ServiceResult createFlight(Flight flight) {
        return null;
    }

    @Override
    public List<Flight> getAllFlight() {
        return null;
    }

    @Override
    public Page<Flight> getFlights(Pageable pageable) {
        return null;
    }

    @Override
    public List<Flight> getFlights(Date start, Date end) {
        return null;
    }

    @Override
    public Page<Flight> getFlights(Date start, Date end, Pageable pageable) {
        return null;
    }

    @Override
    public List<Flight> getFlights(Date start, Date end, AirlineCompany airlineCompany) {
        return null;
    }

    @Override
    public Page<Flight> getFlights(Date start, Date end, AirlineCompany airlineCompany, Pageable pageable) {
        return null;
    }

    @Override
    public List<Flight> getFlights(Date start, Date end, Airport departureAirport, Airport arrivalAirport) {
        return null;
    }

    @Override
    public Page<Flight> getFlights(Date start, Date end, Airport departureAirport, Airport arrivalAirport, Pageable pageable) {
        return null;
    }

    @Override
    public Flight getFlight(Long id) {
        return null;
    }

    @Override
    public ServiceResult modifyFlight(Flight flight) {
        return null;
    }

    @Override
    public ServiceResult deleteFlightById(Long id) {
        return null;
    }

    @Override
    public ServiceResult batchCreateFlight(List<Flight> flights) {
        return null;
    }
}

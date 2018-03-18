package org.neu.cabs.controller;

import lombok.extern.log4j.Log4j;
import org.neu.cabs.constant.FlightState;
import org.neu.cabs.dto.FlightForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Flight;
import org.neu.cabs.orm.Terminal;
import org.neu.cabs.service.AirlineCompanyService;
import org.neu.cabs.service.AirplaneService;
import org.neu.cabs.service.AirportService;
import org.neu.cabs.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 航班管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/flight" })
@Log4j
public class FlightManageController {

    private SimpleDateFormat dateFormat;

    private SimpleDateFormat timeFormat;

    private SimpleDateFormat datetimeFormat;

    private AirlineCompanyService airlineCompanyService;

    private AirplaneService airplaneService;

    private AirportService airportService;

    private FlightService flightService;

    @Autowired
    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Autowired
    public void setTimeFormat(SimpleDateFormat timeFormat) {
        this.timeFormat = timeFormat;
    }

    @Autowired
    public void setDatetimeFormat(SimpleDateFormat datetimeFormat) {
        this.datetimeFormat = datetimeFormat;
    }

    @Autowired
    public void setAirlineCompanyService(AirlineCompanyService airlineCompanyService) {
        this.airlineCompanyService = airlineCompanyService;
    }

    @Autowired
    public void setAirplaneService(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @Autowired
    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        model.addAttribute("flights", flightService.getAllFlight());
        model.addAttribute("companies", airlineCompanyService.getAllAirlineCompany());
        model.addAttribute("airports", airportService.getAllAirport());
        model.addAttribute("airplanes", airplaneService.getAllAirplane());
        model.addAttribute("flightStates", FlightState.values());
        return "manage/flight_manage";
    }

    @RequestMapping(value = { "/createFlight" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<FlightForm> createFlight(@RequestBody FlightForm flightForm) {
        log.info(flightForm);
        Flight flight = form2Flight(flightForm, FlightForm.to(flightForm));
        flight.setId(null);
        ServiceResult serviceResult = flightService.createFlight(flight);
        return ResponseResult.from(serviceResult, FlightForm.from((Flight) serviceResult.getExtra("flight")));
    }

    @RequestMapping(value = { "/deleteFlight" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> deleteFlight(@RequestParam("flightId") long flightId) throws ParseException {
        ServiceResult serviceResult = flightService.deleteFlightById(flightId);
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/modifyFlight" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<FlightForm> modifyFlight(@RequestBody FlightForm flightForm) throws ParseException {
        log.info(flightForm);
        Flight flight = form2Flight(flightForm, FlightForm.to(flightForm, flightService.getFlightById(flightForm.getId())));
        ServiceResult serviceResult = flightService.modifyFlight(flight);
        return ResponseResult.from(serviceResult, FlightForm.from((Flight) serviceResult.getExtra("flight")));
    }

    private Flight form2Flight(FlightForm flightForm, Flight flight) {
        flight.setAirlineCompany(airlineCompanyService.getAirlineCompanyById(flightForm.getAirlineCompany()));
        if (flightForm.getAirplane() != null) {
            flight.setAirplane(airplaneService.getAirplaneById(flightForm.getAirplane()));
        } else {
            flight.setAirplane(null);
        }
        String[] departure = flightForm.getDepartureAirport().split("_");
        String[] arrival = flightForm.getArrivalAirport().split("_");
        flight.setDepartureAirport(airportService.getAirportById(Integer.valueOf(departure[0])));
        flight.setArrivalAirport(airportService.getAirportById(Integer.valueOf(arrival[0])));
        for (Terminal terminal : flight.getDepartureAirport().getTerminals()) {
            if (terminal.getTerminalName().equals(departure[1])) {
                flight.setDepartureTerminal(terminal);
                break;
            }
        }
        for (Terminal terminal : flight.getArrivalAirport().getTerminals()) {
            if (terminal.getTerminalName().equals(arrival[1])) {
                flight.setArrivalTerminal(terminal);
                break;
            }
        }
        return flight;
    }
}

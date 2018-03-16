package org.neu.cabs.controller;

import org.neu.cabs.constant.AirplaneState;
import org.neu.cabs.dto.AirplaneForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airplane;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.service.AirlineCompanyService;
import org.neu.cabs.service.AirplaneService;
import org.neu.cabs.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

/**
 * 飞机管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/airplane" })
public class AirplaneManageController {

    private SimpleDateFormat dateFormat;
    
    private AirlineCompanyService airlineCompanyService;

    private AirplaneService airplaneService;

    private AirportService airportService;

    @Autowired
    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
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

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        List<AirlineCompany> companies = airlineCompanyService.getAllAirlineCompany();
        List<Airplane> airplanes = airplaneService.getAllAirplane();
        List<Airport> airports = airportService.getAllAirport();
        AirplaneState[] airplaneStates = AirplaneState.values();
        model.addAttribute("companies", companies);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("airports", airports);
        model.addAttribute("airplaneStates", airplaneStates);
        return "manage/airplane_manage";
    }

    @RequestMapping(value = { "/createAirplane" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AirplaneForm> createAirplane(@RequestBody AirplaneForm airplaneForm) throws ParseException {
        Airplane airplane = new Airplane();
        airplane.setAirplaneSerialNumber(airplaneForm.getAirplaneSerialNumber());
        airplane.setAirplaneName(airplaneForm.getAirplaneName());
        airplane.setSeatNumber(airplaneForm.getSeatNumber());
        airplane.setBelongToCompany(airlineCompanyService.getAirlineCompanyById(airplaneForm.getBelongToCompany()));
        airplane.setState(AirplaneState.NONE);
        airplane.setFlights(new HashSet<>());
        airplane.setCurrentAirport(null);
        ServiceResult serviceResult = airplaneService.createAirplane(airplane);
        return ResponseResult.from(serviceResult, AirplaneForm.from((Airplane) serviceResult.getExtra("airplane")));
    }

    @RequestMapping(value = { "/deleteAirplane" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> deleteAirplane(@RequestParam("airplaneId") long airplaneId) throws ParseException {
        ServiceResult serviceResult = airplaneService.deleteAirplaneById(airplaneId);
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/modifyAirplane" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AirplaneForm> modifyAirplane(@RequestBody AirplaneForm airplaneForm) throws ParseException {
        Airplane airplane = airplaneService.getAirplaneById(airplaneForm.getId());
        airplane.setAirplaneSerialNumber(airplaneForm.getAirplaneSerialNumber());
        airplane.setAirplaneName(airplaneForm.getAirplaneName());
        airplane.setSeatNumber(airplaneForm.getSeatNumber());
        airplane.setBelongToCompany(airlineCompanyService.getAirlineCompanyById(airplaneForm.getBelongToCompany()));
        airplane.setState(AirplaneState.valueOf(airplaneForm.getState()));
        airplane.setCurrentAirport(airplaneForm.getCurrentAirport() == -1 ?
                null : airportService.getAirportById(airplaneForm.getCurrentAirport()));
        ServiceResult serviceResult = airplaneService.modifyAirplane(airplane);
        return ResponseResult.from(serviceResult, AirplaneForm.from((Airplane) serviceResult.getExtra("airplane")));
    }
}

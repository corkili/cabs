package org.neu.cabs.controller;

import lombok.extern.log4j.Log4j;
import org.neu.cabs.dto.AirportForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * 机场管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/airport" })
@Log4j
public class AirportManageController {

    private AirportService airportService;

    @Autowired
    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        model.addAttribute("airports", airportService.getAllAirport());
        return "manage/airport_manage";
    }

    @RequestMapping(value = { "/createAirport" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AirportForm> createAirport(@RequestBody AirportForm airportForm) throws ParseException {
        log.info(airportForm);
        Airport airport = AirportForm.to(airportForm);
        airport.setId(null);
        ServiceResult serviceResult = airportService.createAirport(airport);
        return ResponseResult.from(serviceResult, AirportForm.from((Airport) serviceResult.getExtra("airport")));
    }

    @RequestMapping(value = { "/deleteAirport" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> deleteAirport(@RequestParam("airportId") int airportId) throws ParseException {
        ServiceResult serviceResult = airportService.deleteAirportById(airportId);
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/modifyAirport" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AirportForm> modifyAirport(@RequestBody AirportForm airportForm) throws ParseException {
        log.info(airportForm);
        Airport airport = AirportForm.to(airportForm, airportService.getAirportById(airportForm.getId()));
        ServiceResult serviceResult = airportService.modifyAirport(airport);
        return ResponseResult.from(serviceResult, AirportForm.from((Airport) serviceResult.getExtra("airport")));
    }
}

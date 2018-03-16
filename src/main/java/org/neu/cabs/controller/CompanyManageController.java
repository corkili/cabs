package org.neu.cabs.controller;

import org.neu.cabs.dto.AirlineCompanyForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.service.AirlineCompanyService;
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
 * 航空公司管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/company" })
public class CompanyManageController {

    private SimpleDateFormat dateFormat;

    private AirlineCompanyService airlineCompanyService;

    @Autowired
    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Autowired
    public void setAirlineCompanyService(AirlineCompanyService airlineCompanyService) {
        this.airlineCompanyService = airlineCompanyService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        List<AirlineCompany> airlineCompanies = airlineCompanyService.getAllAirlineCompany();
        model.addAttribute("airlineCompanies", airlineCompanies);
        return "manage/company_manage";
    }

    @RequestMapping(value = { "/createCompany" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AirlineCompany> createCompany(@RequestBody AirlineCompanyForm airlineCompanyForm) throws ParseException {
        AirlineCompany airlineCompany = new AirlineCompany();
        airlineCompany.setAirplanes(new HashSet<>());
        airlineCompany.setCompanyName(airlineCompanyForm.getCompanyName());
        airlineCompany.setCompanyCode(airlineCompanyForm.getCompanyCode());
        airlineCompany.setEstablishTime(dateFormat.parse(airlineCompanyForm.getEstablishTime()));
        ServiceResult serviceResult = airlineCompanyService.createAirlineCompany(airlineCompany);
        return ResponseResult.from(serviceResult, "airlineCompany");
    }

    @RequestMapping(value = { "/deleteCompany" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> deleteCompany(@RequestParam("companyId") int companyId) throws ParseException {
        ServiceResult serviceResult = airlineCompanyService.deleteAirlineCompanyById(companyId);
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/modifyCompany" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AirlineCompany> modifyCompany(@RequestBody AirlineCompanyForm airlineCompanyForm) throws ParseException {
        AirlineCompany airlineCompany = new AirlineCompany();
        airlineCompany.setAirplanes(new HashSet<>());
        airlineCompany.setId(airlineCompanyForm.getId());
        airlineCompany.setCompanyName(airlineCompanyForm.getCompanyName());
        airlineCompany.setCompanyCode(airlineCompanyForm.getCompanyCode());
        airlineCompany.setEstablishTime(dateFormat.parse(airlineCompanyForm.getEstablishTime()));
        ServiceResult serviceResult = airlineCompanyService.modifyAirlineCompany(airlineCompany);
        return ResponseResult.from(serviceResult, "airlineCompany");
    }

}

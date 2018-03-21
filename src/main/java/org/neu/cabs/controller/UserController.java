package org.neu.cabs.controller;

import lombok.extern.log4j.Log4j;
import org.neu.cabs.dto.PassengerForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.*;
import org.neu.cabs.service.FlightService;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 前台用户综合事务控制器
 * @author 李浩然
 */
@Controller
@Log4j
public class UserController {

    private UserService userService;

    private FlightService flightService;

    private SimpleDateFormat monthFirstDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(value = "/registry", method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> registry(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return ResponseResult.from(userService.registryUser(user));
    }

    @RequestMapping(value = { "/personal_view", "/personal_edit", "/personal_traveller", "/personal_order" }, method = RequestMethod.GET)
    public String personal(Model model, HttpServletRequest request) {
        model.addAttribute("user", userService.getUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ));
        String[] uri = request.getRequestURI().split("/");
        return uri[uri.length - 1];
    }

    @RequestMapping(value = {"/personal_edit"}, method = RequestMethod.POST)
    public String personal(@ModelAttribute(value = "user") User user) throws ParseException {
        User oldUser = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        oldUser.getAddress().setProvince(user.getAddress().getProvince());
        oldUser.getAddress().setCity(user.getAddress().getCity());
        oldUser.setRealName(user.getRealName());
        oldUser.setCertificateType(user.getCertificateType());
        oldUser.setCertificateNumber(user.getCertificateNumber());
        oldUser.setEmail(user.getEmail());
        oldUser.setSex(user.getSex());
        oldUser.setPhone(user.getPhone());
        oldUser.setBirthday(user.getBirthday());
        userService.modifyUserInformation(oldUser);
        return "redirect:/personal_view";
    }

    @RequestMapping(value = {"/addTraveller"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<PassengerForm> addTraveller(@RequestBody PassengerForm passengerForm) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<Long> ids = new HashSet<>();
        for (Passenger passenger : user.getPassengers()) {
            ids.add(passenger.getId());
        }
        user.getPassengers().add(PassengerForm.to(passengerForm));
        ServiceResult result = userService.modifyUserInformation(user);
        User mUser = (User) result.getExtra("user");
        for (Passenger passenger : user.getPassengers()) {
            if (!ids.remove(passenger.getId())) {
                passengerForm = PassengerForm.from(passenger);
                break;
            }
        }
        return ResponseResult.from(result, passengerForm);
    }

    @RequestMapping(value = {"/deletePassenger"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Object> deletePassenger(@RequestParam("passengerId") long id) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Passenger passenger : user.getPassengers()) {
            if (passenger.getId() == id) {
                user.getPassengers().remove(passenger);
                break;
            }
        }
        return ResponseResult.from(userService.modifyUserInformation(user));
    }

    @RequestMapping(value = { "/search" }, method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam("departure") String departure,
                         @RequestParam("arrival") String arrival,
                         @RequestParam("date") String date,
                         @RequestParam("cabin") String cabin) throws ParseException {
        String[] departures = departure.split("-");
        String[] arrivals = arrival.split("-");
        Address from, to;
        if (departures.length == 1) {
            from = new Address("直辖市/特别行政区", departures[0], "");
        } else {
            from = new Address(departures[0], departures[1], "");
        }
        if (arrivals.length == 1) {
            to = new Address("直辖市/特别行政区", arrivals[0], "");
        } else {
            to = new Address(arrivals[0], arrivals[1], "");
        }
        Date departureDate = monthFirstDateFormat.parse(date);
        List<Flight> flights = flightService.searchFlight(from, to, departureDate);
        model.addAttribute("flights", flights);
        model.addAttribute("cabin", cabin);
        return "search";
    }
}

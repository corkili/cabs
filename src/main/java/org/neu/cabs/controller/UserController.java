package org.neu.cabs.controller;

import lombok.extern.log4j.Log4j;
import org.aspectj.weaver.ast.Or;
import org.neu.cabs.constant.CabinType;
import org.neu.cabs.constant.CertificateType;
import org.neu.cabs.constant.OrderState;
import org.neu.cabs.dto.PassengerForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.*;
import org.neu.cabs.service.FlightService;
import org.neu.cabs.service.OrderService;
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
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 前台用户综合事务控制器
 * @author 李浩然
 */
@Controller
@Log4j
public class UserController {

    private UserService userService;

    private FlightService flightService;

    private OrderService orderService;

    private SimpleDateFormat monthFirstDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private SimpleDateFormat dateFormat;

    private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private String[] random = "0 1 2 3 4 5 6 7 8 9 A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    private int maxLength = String.valueOf(Long.MAX_VALUE).length();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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
        User user = userService.getUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        model.addAttribute("user", user);
        String[] uri = request.getRequestURI().split("/");
        if (uri[uri.length - 1].equals("personal_order")) {
            model.addAttribute("orders", user.getOrders());
        }
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
        return "search_flight";
    }

    @RequestMapping(value = { "/purchase" }, method = { RequestMethod.GET })
    public String purchase(Model model, @RequestParam("f") long flightId, @RequestParam("cab") String cabin,
                           HttpServletRequest request) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("flight", flightService.getFlightById(flightId));
        model.addAttribute("user", user);
        model.addAttribute("cabin", cabin);
        model.addAttribute("error", request.getParameter("error"));
        return "purchase";
    }

    @RequestMapping(value = { "/purchaseOrder" }, method = { RequestMethod.POST })
    public String createOrder(Model model, HttpServletRequest request,
                              @RequestParam("f") long flightId,
                              @RequestParam("cab") String cabin) throws ParseException {
        log.info("purchaseOrder " + flightId + " " + cabin);
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Flight flight = flightService.getFlightById(flightId);
        CabinType cabinType = CabinType.valueOf(cabin);
        String[] names = request.getParameterValues("name");
        String[] birthdays = request.getParameterValues("birthday");
        String[] types = request.getParameterValues("certificateType");
        String[] numbers = request.getParameterValues("certificateNumber");
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();
        Double totalCost = 0.0;
        for (int i = 0; i < names.length; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAirportBuildCost(50.0);
            if (cabinType == CabinType.ECONOMY) {
                orderItem.setFlightCost(flight.getEconomyCabinPrice() * flight.getEconomyCabinDiscount());
            } else if (cabinType == CabinType.FISRT) {
                orderItem.setFlightCost(flight.getFirstCabinPrice() * flight.getFirstCabinDiscount());
            } else {
                orderItem.setFlightCost(flight.getBusinessCabinPrice() * flight.getBusinessCabinDiscount());
            }
            orderItem.setCabinType(cabinType);
            orderItem.setFlight(flight);
            orderItem.setOrder(order);
            Passenger passenger = new Passenger();
            passenger.setPassengerName(names[i]);
            passenger.setBirthday(dateFormat.parse(birthdays[i]));
            passenger.setCertificateType(CertificateType.valueOf(types[i]));
            passenger.setCertificateNumber(numbers[i]);
            passenger.setPhone("");
            orderItem.setPassenger(passenger);
            orderItems.add(orderItem);
            totalCost += orderItem.getCost();
        }
        order.setOrderItems(orderItems);
        order.setTotalCost(totalCost);
        order.setCreateTime(new Date());
        order.setState(OrderState.UNPAID);
        order.setBuyer(user);
        order.setSerialNumber(generateOrderSerialNumber(user.getId(), order.getCreateTime()));
        ServiceResult serviceResult = orderService.createOrder(order);
        if (serviceResult.isSuccessful()) {
            model.addAttribute("flight", flight);
            model.addAttribute("order", (Order) serviceResult.getExtra("order"));
            return "purchase_pay";
        } else {
            model.addAttribute("flight", flight);
            model.addAttribute("user", user);
            model.addAttribute("cabin", cabin);
            model.addAttribute("error", serviceResult.getMessage());
            return "purchase";
        }
    }

    @RequestMapping(value = { "/payOrder" }, method = { RequestMethod.POST })
    public String payOrder(@RequestParam("o") long orderId) {
        ServiceResult serviceResult = orderService.payOrder(orderId);
        log.info(serviceResult.isSuccessful() + " " + serviceResult.getMessage());
        return "redirect:personal";
    }

    @RequestMapping(value = { "/payOrder" }, method = { RequestMethod.GET })
    public String payOrder(Model model, @RequestParam("o") long orderId) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("flight", order.getOrderItems().iterator().next().getFlight());
        model.addAttribute("order", order);
        return "purchase_pay";
    }

    @RequestMapping(value = { "/refund" }, method = { RequestMethod.POST })
    public String refundOrder(@RequestParam("o") long orderId) {
        ServiceResult serviceResult = orderService.refundOrder(orderId);
        log.info(serviceResult.isSuccessful() + " " + serviceResult.getMessage());
        return "redirect:personal";
    }

    @RequestMapping(value = { "/refund" }, method = { RequestMethod.GET })
    public String refundOrder(Model model, @RequestParam("o") long orderId) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("flight", order.getOrderItems().iterator().next().getFlight());
        model.addAttribute("order", order);
        return "refund";
    }

    @RequestMapping(value = { "/endorse" }, method = { RequestMethod.GET })
    public String endorseOrder(@RequestParam("o") Long orderId, HttpSession session) {
        session.setAttribute("ENDORSE_ORDER", orderId);
        return "redirect:search";
    }

    @RequestMapping(value = { "/endorseOrder" }, method = { RequestMethod.GET })
    public String endorseOrder(Model model, @RequestParam("f") long flightId, @RequestParam("cab") String cabin,
                           @SessionAttribute("ENDORSE_ORDER") Long orderId, HttpSession session) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Flight flight = flightService.getFlightById(flightId);
        CabinType cabinType = CabinType.valueOf(cabin);
        Order order = orderService.getOrderById(orderId);
        Double totalCost = 0.0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (cabinType == CabinType.ECONOMY) {
                orderItem.setFlightCost(flight.getEconomyCabinPrice() * flight.getEconomyCabinDiscount());
            } else if (cabinType == CabinType.FISRT) {
                orderItem.setFlightCost(flight.getFirstCabinPrice() * flight.getFirstCabinDiscount());
            } else {
                orderItem.setFlightCost(flight.getBusinessCabinPrice() * flight.getBusinessCabinDiscount());
            }
            orderItem.setCabinType(cabinType);
            orderItem.setFlight(flight);
            orderItem.setOrder(order);
            totalCost += orderItem.getCost();
        }
        order.setTotalCost(totalCost);
        order.setState(OrderState.UNPAID);
        order.setSerialNumber(generateOrderSerialNumber(user.getId(), order.getCreateTime()));
        ServiceResult serviceResult = orderService.endorseOrder(order);
        model.addAttribute("flight", flight);
        model.addAttribute("order", (Order) serviceResult.getExtra("order"));
        session.removeAttribute("ENDORSE_ORDER");
        return "purchase_pay";
    }

    @RequestMapping(value = { "/view/{serial}" }, method = { RequestMethod.GET })
    public String purchase(Model model, @PathVariable("serial") String orderSerialNumber) {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Order order = orderService.getOrderBySerialNumber(orderSerialNumber);
        model.addAttribute("order", order);
        model.addAttribute("flight", order.getOrderItems().iterator().next().getFlight());
        model.addAttribute("user", user);
        model.addAttribute("cabin", order.getOrderItems().iterator().next().getCabinType());
        return "view";
    }

    private String generateOrderSerialNumber(long userId, Date time) {
        Random rd = new Random(time.getTime() + userId);
        String id = String.valueOf(userId);
        int diff = maxLength - id.length();
        StringBuilder serial = new StringBuilder(datetimeFormat.format(time));
        for (int i = 0; i < diff; i++) {
            serial.append(random[rd.nextInt(random.length)]);
        }
        serial.append(userId);
        return serial.toString();
    }
}

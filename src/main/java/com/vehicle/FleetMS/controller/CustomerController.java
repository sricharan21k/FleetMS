package com.vehicle.FleetMS.controller;

import com.vehicle.FleetMS.Enum.VehicleStatus;
import com.vehicle.FleetMS.model.Booking;
import com.vehicle.FleetMS.model.Customer;
import com.vehicle.FleetMS.model.Vehicle;
import com.vehicle.FleetMS.repository.BookingRepository;
import com.vehicle.FleetMS.service.CustomerService;
import com.vehicle.FleetMS.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/customers")
    public String customers(Model model) {

        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers
        ) {
            List<Booking> bookings = customer.getBookings();
            if (!bookings.isEmpty()) {
                bookings.sort(Comparator.comparing(Booking::getBookingDate).reversed());
                customer.setLastBooking(bookings.get(0));
            }
        }
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/add")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "new-customer";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("imageFile") MultipartFile file) {
        customerService.addCustomer(customer, file);
        return "redirect:/customers";
    }

    @GetMapping("/customers/bookings/{id}")
    public String viewBookings(@PathVariable Long id, Model model) {
        List<Booking> bookings = customerService.getCustomer(id).getBookings();
        for (Booking booking: bookings
             ) {
            Duration duration = Duration.between(booking.getBookingDate(), booking.getEndDate());
            long hours = duration.toHours();
            booking.setTotalFare(booking.getVehicle().getCostPerHour()*hours);
        }
        model.addAttribute("customerId", id);
        model.addAttribute("bookings", bookings);
        return "customer-bookings";
    }

    @GetMapping("/customers/bookings/add/{id}")
    public String newBooking(@PathVariable Long id, Model model) {
        System.out.println("path variable " + id);
        model.addAttribute("booking", new Booking());
        model.addAttribute("customerId", id);
        model.addAttribute("vehicles", vehicleService.getAvailableVehicles());
        return "new-booking";
    }

    @PostMapping("/customers/bookings/add")
    public String newBooking(Booking booking, @RequestParam("vehicleId") Long vehicleId, @RequestParam("customerId") Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        booking.setCustomer(customer);
        bookingRepository.save(booking);
        Vehicle vehicle = vehicleService.getVehicle(vehicleId).get();
        vehicle.setBooking(booking);
        vehicle.setStatus(VehicleStatus.BOOKED);
        vehicleService.updateVehicle(vehicle);

        customer.getBookings().add(booking);
        System.out.println("booking date " + customer.getBookings().get(0).getBookingDate());
        customerService.updateCustomer(customer);
        return "redirect:/customers/bookings/" + customerId;
    }
}

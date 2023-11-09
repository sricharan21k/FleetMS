package com.vehicle.FleetMS;

import com.vehicle.FleetMS.model.*;
import com.vehicle.FleetMS.repository.*;
import com.vehicle.FleetMS.service.BookingService;
import com.vehicle.FleetMS.service.DriverService;
import com.vehicle.FleetMS.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FleetMsApplication implements CommandLineRunner {
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingService bookingService;

	private Double calculateTotalRevenue(List<Booking> bookings){
		return bookings.stream().mapToDouble(Booking::getTotalFare).sum();
	}
	public static void main(String[] args) {
		SpringApplication.run(FleetMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Booking booking = bookingRepository.findById(1L).get();

//		List<Booking> bookings = bookingService.getBookings();
//		System.out.println(bookings.size());
//		System.out.println(calculateTotalRevenue(bookings));

//		Driver driver = new Driver();
//		driver.setFirstname("Robot");
//		Address address = new Address();
//		address.setCity("LA");
//		driver.setAddress(address);
//
//		Vehicle vehicle = new Vehicle();
//		vehicle.setMake("Tesla");
//		vehicle.setDriver(driver);
//		vehicleRepository.save(vehicle);
//
//		Address address1 = new Address();
//		address1.setCity("NYC");
//		Customer customer = new Customer();
//		customer.setFirstname("Shree");
//		customer.setAddress(address1);
//		customerRepository.save(customer);
//
//		Address address2 = new Address();
//		address2.setCity("LA");
//		Booking booking = new Booking();
//		booking.setAddress(address2);
//		booking.setBookingDate(new Date());
//		booking.setVehicle(vehicle);
//		booking.setCustomer(customer);
//
//		bookingRepository.save(booking);
//		Booking booking = bookingRepository.findById(4L).get();
//		System.out.println(booking.getVehicle().getMake());
//		Driver driver = driverRepository.findById(2L).get();
//		System.out.println(driver.getVehicle().getMake());
//		Vehicle vehicle = new Vehicle();
//		vehicle.setMake("Syan");
//		vehicle.setModel("Xone");
//		Driver driver = new Driver();
//		driver.setFirstname("hello");
//		driver.setLastname("world");
//		vehicle.setDriver(driver);
//		vehicleRepository.save(vehicle);






	}
}

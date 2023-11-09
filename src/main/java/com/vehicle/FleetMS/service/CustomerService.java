package com.vehicle.FleetMS.service;

import com.vehicle.FleetMS.model.Customer;
import com.vehicle.FleetMS.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(!file.isEmpty()){
            Path path = Paths.get("src/main/resources/static/img/customer", fileName);
            try {
                Files.write(path, file.getBytes());
                customer.setImage("/img/customer/"+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }
}

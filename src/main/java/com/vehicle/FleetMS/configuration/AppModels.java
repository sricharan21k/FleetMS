package com.vehicle.FleetMS.configuration;

import com.vehicle.FleetMS.model.User;
import com.vehicle.FleetMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AppModels {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("user")
    public User globalModel(){
        return userRepository.findById(1L).get();
    }
}

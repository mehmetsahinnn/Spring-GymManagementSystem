package com.example.gigagym.services;


import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;


    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> listAll() {
        return staffRepository.findAll();
    }


    public boolean authenticate(String name, String password) {
        Staff user = staffRepository.findByName(name);
        return user != null && user.getPassword().equals(password);
    }

    public Staff authenticate(String email) {
        return staffRepository.findByEmailAddress(email);
    }

    public void updateStaff(Integer id, String name, String emailAddress, String jobTitle, Integer daysOfWork, Date startDate) {
        staffRepository.updateStaff(id, name, emailAddress, jobTitle, daysOfWork, startDate);
    }
}
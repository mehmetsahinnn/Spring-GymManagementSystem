package com.example.gigagym.services;


import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Staff authenticate(String email) {
        return staffRepository.findByEmail(email);
    }
}
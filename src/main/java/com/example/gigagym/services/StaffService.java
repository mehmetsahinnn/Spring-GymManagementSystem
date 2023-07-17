package com.example.gigagym.services;

import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StaffService {

    private final StaffRepository staffRepository;


    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    public void updateStaff(Integer id, String name, String emailAddress, String password, String jobTitle, Integer daysOfWork, Date startDate) {
        staffRepository.updateStaff(id, name, emailAddress, password, jobTitle, daysOfWork, startDate);
    }

    public Page<Staff> findStaffByName(String name, Pageable pageable) {
        Specification<Staff> spec = (root, query, cb) -> {
            if (name != null) {
                return cb.like(root.get("name"), "%" + name + "%");
            } else {
                return cb.conjunction();
            }
        };
        return staffRepository.findAll(spec, pageable);
    }
}
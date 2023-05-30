package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import com.example.gigagym.services.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@Controller
public class StaffController {

    private final StaffService staffService;
    private final StaffRepository staffRepository;

    public StaffController(StaffService staffService, StaffRepository staffRepository) {
        this.staffService = staffService;
        this.staffRepository = staffRepository;
    }

    @GetMapping("/staff")
    public String staff(Model model, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        List<Staff> listStaff = staffService.listAll();
        model.addAttribute("listStaff", listStaff);

        if ("Maintenance Guy".equals(JobTitle)) {

            return "redirect:/maintenance";
        } else if ("Personal Trainer".equals(JobTitle)) {
            return "redirect:/home";
        } else {
            return "staff";
        }
    }


    @PostMapping("/staff")
    public String saveData(@RequestParam("name") String name,
                           @RequestParam("emailAddress") String emailAddress,
                           @RequestParam("jobTitle") String jobTitle,
                           @RequestParam("daysOfWork") int daysOfWork,
                           @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setEmailAddress(emailAddress);
        staff.setJobTitle(jobTitle);
        staff.setDaysOfWork(daysOfWork);
        staff.setStartDate(startDate);
        staffRepository.save(staff);
        return "redirect:/home";
    }

    @PostMapping("/updateStaff")
    public String updateData(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("emailAddress") String emailAddress,
                             @RequestParam("jobTitle") String jobTitle,
                             @RequestParam("daysOfWork") int daysOfWork,
                             @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        staffService.updateStaff(id, name, emailAddress, jobTitle, daysOfWork, startDate);
        return "redirect:/staff";
    }

    @DeleteMapping("/deleteStaff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Integer id) {
        staffRepository.deleteStaff(id);
        return ResponseEntity.ok().build();
    }
}
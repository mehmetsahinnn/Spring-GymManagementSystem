package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import com.example.gigagym.services.EquipmentService;
import com.example.gigagym.services.StaffService;
import com.example.gigagym.util.UtilityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Date;

@Controller
public class StaffController {

    private final StaffService staffService;
    private final StaffRepository staffRepository;
    private final EquipmentService equipmentService;
    private final UtilityService utilityService;

    public StaffController(StaffService staffService, StaffRepository staffRepository, EquipmentService equipmentService, UtilityService utilityService) {
        this.staffService = staffService;
        this.staffRepository = staffRepository;
        this.equipmentService = equipmentService;
        this.utilityService = utilityService;
    }

    @GetMapping("/staff")
    public Object staff(Model model, @PageableDefault(size = 250) Pageable pageable, @RequestParam(required = false) String name, HttpSession session, HttpServletRequest request) {

        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        Page<Staff> staff = staffService.findStaffByName(name, pageable);
        model.addAttribute("listStaff", staff);

        String requestedWithHeader = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWithHeader)) {
            return ResponseEntity.ok(staff);
        } else {
            if ("Maintenance Guy".equals(JobTitle)) {

                return "redirect:/maintenance";
            } else if ("Personal Trainer".equals(JobTitle)) {
                return "redirect:/home";
            } else {
                return "staff";
            }
        }
    }


    @PostMapping("/staff")
    public String newStaff(@RequestParam("name") String name,
                           @RequestParam("emailAddress") String emailAddress,
                           @RequestParam("password") String password,
                           @RequestParam("jobTitle") String jobTitle,
                           @RequestParam("daysOfWork") int daysOfWork,
                           @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setEmailAddress(emailAddress);
        staff.setPassword(password);
        staff.setJobTitle(jobTitle);
        staff.setDaysOfWork(daysOfWork);
        staff.setStartDate(startDate);
        staffRepository.save(staff);
        return "redirect:/home";
    }

    @PostMapping("/updateStaff")
    public String updateData(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam("emailAddress") String emailAddress,
                             @RequestParam("jobTitle") String jobTitle,
                             @RequestParam("daysOfWork") int daysOfWork,
                             @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        staffService.updateStaff(id, name, emailAddress, password, jobTitle, daysOfWork, startDate);
        return "redirect:/staff";
    }

    @DeleteMapping("/deleteStaff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Integer id) {
        staffRepository.deleteStaff(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/topSearchStaff")
    public ModelAndView search(@RequestParam("page") String pageName) {
        if (utilityService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/staff");
        }
    }

    @RequestMapping("/searchStaff/{name}")
    public ModelAndView searchEquipment(@PathVariable String name) {
        if (name == null) {
            return new ModelAndView("redirect:/staff");
        }
        return new ModelAndView("redirect:/staff?name=" + name);
    }
}
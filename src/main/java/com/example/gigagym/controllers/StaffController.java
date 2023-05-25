package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.services.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/table-2")
    public String staff(Model model) {
        List<Staff> listStaff = staffService.listAll();
        model.addAttribute("listStaff", listStaff);
        return "table-2";
    }
}
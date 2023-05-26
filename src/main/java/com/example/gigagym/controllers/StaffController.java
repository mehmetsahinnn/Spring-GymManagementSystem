package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.services.StaffService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/staff")
    public String staff(Model model, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        List<Staff> listStaff = staffService.listAll();
        model.addAttribute("listStaff", listStaff);
        return "table-2";
    }
}
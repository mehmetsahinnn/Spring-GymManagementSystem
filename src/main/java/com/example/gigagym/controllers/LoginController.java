package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.services.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    private final StaffService staffService;

    public LoginController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/")
    public String login(@RequestParam String email, Model model, HttpSession session) {
        Staff staff = staffService.authenticate(email);
        if (staff!=null) {
            session.setAttribute("StaffName", staff.getName());
            session.setAttribute("JobTitle", staff.getJobTitle());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Email not found");
            return "login";
        }
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        return "profile";
    }

    @GetMapping("/forgot-password")
    public String forgotPass() {
        return "forgot-password";
    }


}
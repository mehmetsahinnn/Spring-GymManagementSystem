package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import com.example.gigagym.services.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final StaffService staffService;
    public final StaffRepository staffRepository;

    public LoginController(StaffService staffService, StaffRepository staffRepository) {
        this.staffService = staffService;
        this.staffRepository = staffRepository;
    }

@PostMapping("/")
public String login(@RequestParam("name") String name,
                    @RequestParam("password") String password,
                    Model model,
                    HttpSession session) {
    boolean authenticated = staffService.authenticate(name, password);

    if (authenticated) {
        Staff staff = staffRepository.findByName(name);

        session.setAttribute("StaffName", staff.getName());
        session.setAttribute("JobTitle", staff.getJobTitle());

        return "redirect:/home";
    } else {
        model.addAttribute("error", "Invalid username or password");
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
}
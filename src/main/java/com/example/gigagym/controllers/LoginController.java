package com.example.gigagym.controllers;

import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.StaffRepository;
import com.example.gigagym.services.LoginService;
import com.example.gigagym.repositories.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LoginService loginService;
    public final StaffRepository staffRepository;
    public final LoginRepository loginRepository;

    public LoginController(LoginService loginService, StaffRepository staffRepository, LoginRepository loginRepository) {
        this.loginService = loginService;
        this.staffRepository = staffRepository;
        this.loginRepository = loginRepository;
    }

@PostMapping("/")
public String login(@RequestParam("name") String emailAddress,
                    @RequestParam("password") String password,
                    Model model,
                    HttpSession session) {
    boolean authenticated = loginService.authenticate(emailAddress, password);

    if (authenticated) {
        Staff staff = loginRepository.findByEmailAddress(emailAddress);

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
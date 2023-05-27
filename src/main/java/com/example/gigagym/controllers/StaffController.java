package com.example.gigagym.controllers;

import com.example.gigagym.models.Payment;
import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.PaymentRepository;
import com.example.gigagym.services.PaymentService;
import com.example.gigagym.services.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class StaffController {

    private final StaffService staffService;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    public StaffController(StaffService staffService, PaymentService paymentService, PaymentRepository paymentRepository) {
        this.staffService = staffService;
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
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

    @GetMapping("/payment")
    public String payment(Model model, @PageableDefault(size = 20) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        List<Payment> payment = paymentService.listPayment();
        model.addAttribute("payment", payment);

        if ("Personal Trainer".equals(JobTitle)) {
            return "redirect:/home";
        }
        if ("Maintenance Guy".equals(JobTitle)) {
            return "redirect:/home";
        } else {
            return "payment";
        }
    }
}
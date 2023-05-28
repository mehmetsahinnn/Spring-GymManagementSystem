package com.example.gigagym.controllers;

import com.example.gigagym.models.Payment;
import com.example.gigagym.repositories.PaymentRepository;
import com.example.gigagym.services.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    public PaymentController(PaymentService paymentService, PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
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

    @PostMapping("/payment")
    public String saveData(@RequestParam("SID") int SID,
                           @RequestParam("salary") int salary,
                           @RequestParam("paymentStatus") int paymentStatus,
                           @RequestParam("paymentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate) {
        Payment payment = new Payment();
        payment.setSID(SID);
        payment.setSalary(salary);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentDate(paymentDate);
        paymentRepository.save(payment);
        return "redirect:/home";
    }

}
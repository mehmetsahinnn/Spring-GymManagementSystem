package com.example.gigagym.controllers;

import com.example.gigagym.models.Payment;
import com.example.gigagym.repositories.PaymentRepository;
import com.example.gigagym.services.PaymentService;
import com.example.gigagym.util.UtilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    private final UtilityService utilityService;

    public PaymentController(PaymentService paymentService, PaymentRepository paymentRepository, UtilityService utilityService) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
        this.utilityService = utilityService;
    }

    @GetMapping("/payment")
    public String payment(Model model, @PageableDefault(size = 20) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        double totalPaymentDividedByFive = paymentService.calculateTotalPayment();
        session.setAttribute("totalPaymentDividedByFive", totalPaymentDividedByFive);
        model.addAttribute("totalPaymentDividedByFive", totalPaymentDividedByFive);

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
        return "redirect:/payment";
    }

    @PostMapping("/payment/update")
    public String updatePaymentStatus(@RequestParam("paymentId") Long paymentId, HttpSession session) throws Exception {
        double totalPaymentDividedByFive = paymentService.calculateTotalPayment();
        session.setAttribute("totalPaymentDividedByFive", totalPaymentDividedByFive);

        paymentService.updatePaymentStatus(paymentId, 1);
        return "redirect:/payment";
    }

    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Integer id) {
        paymentRepository.deletePayment(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/topSearchPayment")
    public ModelAndView search(@RequestParam("page") String pageName) {
        if (utilityService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/payment");
        }
    }
}

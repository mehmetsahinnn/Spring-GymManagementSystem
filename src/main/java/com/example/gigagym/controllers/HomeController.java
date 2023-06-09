package com.example.gigagym.controllers;


import com.example.gigagym.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final MemberService memberService;
    private final PaymentService paymentService;
    private final EquipmentService equipmentService;
    private final MaintenanceService maintenanceService;


    public HomeController(MemberService memberService, PaymentService paymentService, EquipmentService equipmentService, MaintenanceService maintenanceService) {
        this.memberService = memberService;
        this.paymentService = paymentService;
        this.equipmentService = equipmentService;
        this.maintenanceService = maintenanceService;
    }

    @RequestMapping("/home")
    public String index(Model model, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");

        model.addAttribute("numberOfUsersLastMonth", memberService.getNumberOfUsersRegisteredLastMonth());
        model.addAttribute("lastMonthEarnings", memberService.calculateLastMonthEarnings());
        model.addAttribute("totalCharges", memberService.calculateTotalCharges());
        model.addAttribute("totalPayment", paymentService.calculateTotalPayment());
        model.addAttribute("mostCommonEquipmentType", equipmentService.getMostCommonEquipmentType());
        model.addAttribute("maleRatio", memberService.getMaleRatio() * 100);
        model.addAttribute("femaleRatio", memberService.getFemaleRatio() * 100);
        model.addAttribute("maintenanceCount", maintenanceService.nextMaintenance());
        model.addAttribute("StaffName", StaffName);


        if ("Maintenance Guy".equals(JobTitle)) {

            return "redirect:/maintenance";
        }
        if ("Personal Trainer".equals(JobTitle)) {

            return "redirect:/members";
        }
        else {
            return "home";
        }
    }
}
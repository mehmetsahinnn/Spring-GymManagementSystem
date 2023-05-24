package com.example.gigagym.controllers;


import com.example.gigagym.services.EquipmentService;
import com.example.gigagym.services.MaintenanceService;
import com.example.gigagym.services.MemberService;
import com.example.gigagym.services.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    private final MemberService memberService;
    private final PaymentService paymentService;
    private final MaintenanceService maintenanceService;
    private final EquipmentService equipmentService;


    public HomeController(MemberService memberService, PaymentService paymentService, MaintenanceService maintenanceService, EquipmentService equipmentService) {
        this.memberService = memberService;
        this.paymentService = paymentService;
        this.maintenanceService = maintenanceService;
        this.equipmentService = equipmentService;
    }

    @RequestMapping("/home")
    public String index(Model model) {

        model.addAttribute("numberOfUsersLastMonth", memberService.getNumberOfUsersRegisteredLastMonth());
        model.addAttribute("lastMonthEarnings", memberService.calculateLastMonthEarnings());
        model.addAttribute("totalCharges", memberService.calculateTotalCharges());
        model.addAttribute("totalPayment", paymentService.calculateTotalPaymentDividedByFive());
        model.addAttribute("UpcomingMaintenance", maintenanceService.getUpcomingMaintenanceEquipmentCount());
        model.addAttribute("mostCommonEquipmentType", equipmentService.getMostCommonEquipmentType());

        return "index";
    }
}
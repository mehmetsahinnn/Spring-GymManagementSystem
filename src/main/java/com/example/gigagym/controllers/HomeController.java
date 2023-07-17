package com.example.gigagym.controllers;


import com.example.gigagym.services.*;
import com.example.gigagym.util.UtilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final MemberService memberService;
    private final PaymentService paymentService;
    private final EquipmentService equipmentService;
    private final MaintenanceService maintenanceService;
    private final UtilityService utilityService;


    public HomeController(MemberService memberService, PaymentService paymentService, EquipmentService equipmentService, MaintenanceService maintenanceService, UtilityService utilityService) {
        this.memberService = memberService;
        this.paymentService = paymentService;
        this.equipmentService = equipmentService;
        this.maintenanceService = maintenanceService;
        this.utilityService = utilityService;
    }

    @GetMapping("/home")
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
        } else {
            return "home";
        }
    }

    @RequestMapping("/topSearchHome")
    public ModelAndView search(@RequestParam("page") String pageName) {
        if (utilityService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/home");
        }
    }
}
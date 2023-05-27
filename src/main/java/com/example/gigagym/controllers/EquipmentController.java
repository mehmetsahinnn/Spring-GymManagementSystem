package com.example.gigagym.controllers;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Maintenance;
import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MaintenanceRepository;
import com.example.gigagym.services.MaintenanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    final EquipmentRepository equipmentRepository;
    final MaintenanceRepository maintenanceRepository;
    private final MaintenanceService maintenanceService;

    public EquipmentController(EquipmentRepository equipmentRepository, MaintenanceRepository maintenanceRepository, MaintenanceService maintenanceService) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/equipment")
    public String table1(Model model, @PageableDefault(size = 100) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");

        model.addAttribute("StaffName", StaffName);
        Page<Equipment> equipment = equipmentRepository.findAll(pageable);
        model.addAttribute("equipment", equipment);

        if ("Maintenance Guy".equals(JobTitle)) {

            return "redirect:/maintenance";
        }
        else if ("Personal Trainer".equals(JobTitle)){
            return "redirect:/home";
        }
        else {
            return "equipment";
        }
    }

    @GetMapping("/maintenance")
    public String maintenance(Model model, @PageableDefault(size = 100) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        Page<Maintenance> maintenance = maintenanceService.getMaintenancesForNextWeek(pageable);
        model.addAttribute("maintenance", maintenance);

        if ("Personal Trainer".equals(JobTitle)){
            return "redirect:/home";
        }
        else {
            return "maintenance";
        }
    }

}

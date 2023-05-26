package com.example.gigagym.controllers;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Maintenance;
import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MaintenanceRepository;
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

    public EquipmentController(EquipmentRepository equipmentRepository, MaintenanceRepository maintenanceRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @GetMapping("/equipment")
    public String table1(Model model, @PageableDefault(size = 100) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        Page<Equipment> equipment = equipmentRepository.findAll(pageable);
        model.addAttribute("equipment", equipment);

        return "table-1";
    }

    @GetMapping("/maintenance")
    public String maintenance(Model model, @PageableDefault(size = 100) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        Page<Maintenance> maintenance = maintenanceRepository.findAll(pageable);
        model.addAttribute("maintenance", maintenance);
        return "maintenance";
    }

}

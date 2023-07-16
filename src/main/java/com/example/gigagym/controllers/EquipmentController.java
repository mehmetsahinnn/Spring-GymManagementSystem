package com.example.gigagym.controllers;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Maintenance;
import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MaintenanceRepository;
import com.example.gigagym.services.EquipmentService;
import com.example.gigagym.services.MaintenanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
public class EquipmentController {
    final EquipmentRepository equipmentRepository;
    final EquipmentService equipmentService;
    final MaintenanceRepository maintenanceRepository;
    private final MaintenanceService maintenanceService;

    public EquipmentController(EquipmentRepository equipmentRepository, EquipmentService equipmentService, MaintenanceRepository maintenanceRepository, MaintenanceService maintenanceService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentService = equipmentService;
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceService = maintenanceService;
    }


    @GetMapping("/equipment")
    public Object table1(Model model, @PageableDefault(size = 250) Pageable pageable, @RequestParam(required = false) String type, HttpSession session, HttpServletRequest request) {

        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        Page<Equipment> eq = equipmentService.findEquipmentsByType(type, pageable);
        model.addAttribute("equipment", eq);


        String requestedWithHeader = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWithHeader)) {
            return ResponseEntity.ok(eq);
        } else {
            if ("Maintenance Guy".equals(JobTitle)) {
                return "redirect:/maintenance";
            } else if ("Personal Trainer".equals(JobTitle)) {
                return "redirect:/home";
            } else {
                return "equipment";
            }
        }

    }

    @PostMapping("/equipment")
    public String saveEquipment(@RequestParam("type") String type,
                                @RequestParam("manufacturer") String manufacturer,
                                @RequestParam("model") String model,
                                @RequestParam("purchaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date purchaseDate) {
        Equipment equipment = new Equipment();
        equipment.setType(type);
        equipment.setManufacturer(manufacturer);
        equipment.setModel(model);
        equipment.setPurchaseDate(purchaseDate);
        equipmentRepository.save(equipment);
        return "redirect:/home";
    }

    @RequestMapping("/searchEquipment/{name}")
    public ModelAndView searchEquipment(@PathVariable String name) {
        if (name == null) {
            return new ModelAndView("redirect:/equipment");
        }
        return new ModelAndView("redirect:/equipment?type=" + name);
    }

    @PostMapping("/updateEquipment")
    public String updateEquipment(@RequestParam("id") Integer id,
                                  @RequestParam("type") String type,
                                  @RequestParam("manufacturer") String manufacturer,
                                  @RequestParam("model") String model,
                                  @RequestParam("purchaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date purchaseDate) {
        equipmentRepository.updateEquipment(id, type, manufacturer, model, purchaseDate);
        return "redirect:/equipment";
    }

    @DeleteMapping("/deleteEquipment/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/maintenance")
    public String maintenance(Model model, @PageableDefault(size = 100) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        String JobTitle = (String) session.getAttribute("JobTitle");
        model.addAttribute("StaffName", StaffName);

        Page<Maintenance> maintenance = maintenanceService.getMaintenancesForNextWeek(pageable);
        model.addAttribute("maintenance", maintenance);

        if ("Personal Trainer".equals(JobTitle)) {
            return "redirect:/home";
        } else {
            return "maintenance";
        }
    }

    @PostMapping("/maintenance")
    public String saveData(@RequestParam("equipmentId") int equipmentId,
                           @RequestParam("maintenanceId") int maintenanceId,
                           @RequestParam("dateOfNextMaintenance") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfNextMaintenance,
                           @RequestParam("dateOfMaintenance") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfMaintenance) {
        Maintenance maintenance = new Maintenance();
        maintenance.setEquipmentId(equipmentId);
        maintenance.setMaintenanceId(maintenanceId);
        maintenance.setDateOfMaintenance(dateOfMaintenance);
        maintenance.setDateOfNextMaintenance(dateOfNextMaintenance);
        maintenanceRepository.save(maintenance);
        return "redirect:/home";
    }

    @DeleteMapping("/deleteMaintenance/{id}")
    public ResponseEntity<?> deleteMaintenance(@PathVariable Integer id) {
        maintenanceRepository.deleteMaintenance(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/topSearchEquipment")
    public ModelAndView EquipmentPageExists(@RequestParam("page") String pageName) {
        if (equipmentService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/equipment");
        }
    }


    @RequestMapping("/topSearch")
    public ModelAndView MaintenancePageExists(@RequestParam("page") String pageName) {
        if (equipmentService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/maintenance");
        }
    }


}

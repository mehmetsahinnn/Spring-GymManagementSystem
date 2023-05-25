package com.example.gigagym.controllers;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Member;
import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EquipmentController {
    final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/equipment")
    public String table1(Model model, @PageableDefault(size = 100) Pageable pageable) {
        Page<Equipment> equipment = equipmentRepository.findAll(pageable);
        model.addAttribute("equipment", equipment);
        return "table-1";
    }

}

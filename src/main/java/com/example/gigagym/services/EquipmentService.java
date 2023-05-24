package com.example.gigagym.services;

import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public String getMostCommonEquipmentType() {
        List<Object[]> results = equipmentRepository.countEquipmentByType();

        if (results.isEmpty()) {
            return null;
        }

        return (String) results.get(0)[0];
    }
}
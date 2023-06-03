package com.example.gigagym.services;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Maintenance;
import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceRepository maintenanceRepository;

    public EquipmentService(EquipmentRepository equipmentRepository, MaintenanceRepository maintenanceRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    public String getMostCommonEquipmentType() {
        List<Object[]> results = equipmentRepository.countEquipmentByType();

        if (results.isEmpty()) {
            return null;
        }

        return (String) results.get(0)[0];
    }


    public void deleteStaff(Long id) {
        equipmentRepository.deleteEquipment(id);
    }


    public boolean pageExists(String pageName) {
        Path filePath = Paths.get("src/main/resources/templates/" + pageName + ".html");
        return Files.exists(filePath);
    }
}
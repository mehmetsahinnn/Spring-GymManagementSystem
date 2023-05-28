package com.example.gigagym.services;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Maintenance;
import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.EquipmentRepository;
import com.example.gigagym.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

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

    public int nextMaintenance() {
        Calendar currentDate = Calendar.getInstance();
        Calendar nextWeekDate = Calendar.getInstance();
        nextWeekDate.add(Calendar.DATE, 7);
        List<Maintenance> maintenanceList = maintenanceRepository.findByDateOfNextMaintenanceBetween(currentDate.getTime(), nextWeekDate.getTime());
        return maintenanceList.size();
    }

    public void deleteStaff(Long id) {
        equipmentRepository.deleteById(id);
    }


}
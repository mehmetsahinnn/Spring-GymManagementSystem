package com.example.gigagym.services;


import com.example.gigagym.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public int getUpcomingMaintenanceEquipmentCount() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return maintenanceRepository.countEquipmentWithUpcomingMaintenance(date);
    }
}

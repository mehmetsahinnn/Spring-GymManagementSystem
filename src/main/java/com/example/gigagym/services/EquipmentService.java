package com.example.gigagym.services;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.repositories.EquipmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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


    public void deleteEquipment(Long id) throws Exception {
    Optional<Equipment> equipment = equipmentRepository.findById(id);
    if (equipment.isPresent()) {
        equipmentRepository.deleteById(id);
    } else {
        throw new Exception("Equipment with id " + id + " not found");
    }
}


    public Page<Equipment> findEquipmentsByType(String type, Pageable pageable) {
        Specification<Equipment> spec = (root, query, cb) -> {
            if (type != null) {
                return cb.like(root.get("type"), "%" + type + "%");
            } else {
                return cb.conjunction();
            }
        };
        return equipmentRepository.findAll(spec, pageable);
    }


}
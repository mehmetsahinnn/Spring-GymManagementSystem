package com.example.gigagym.services;

import com.example.gigagym.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;


    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public double calculateTotalPaymentDividedByFive() {
        List<Object[]> results = paymentRepository.calculateTotalPaymentsBySID();

        double totalPayment = 0.0;
        for (Object[] result : results) {
            double sumOfSalaries = (Double) result[1];
            totalPayment += sumOfSalaries;
        }

        return totalPayment / 5;
    }
}

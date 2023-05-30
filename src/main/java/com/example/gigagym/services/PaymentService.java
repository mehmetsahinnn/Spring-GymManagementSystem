package com.example.gigagym.services;

import com.example.gigagym.models.Payment;
import com.example.gigagym.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

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

    public List<Payment> listPayment() {
        return paymentRepository.findAll();
    }

    public void updatePaymentStatus(Long paymentId, int newStatus) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            payment.setPaymentStatus(newStatus);
            paymentRepository.save(payment);
        }
    }
}

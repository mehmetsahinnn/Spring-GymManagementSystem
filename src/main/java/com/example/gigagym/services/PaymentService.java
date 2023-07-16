package com.example.gigagym.services;

import com.example.gigagym.models.Payment;
import com.example.gigagym.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;


    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public double calculateTotalPayment() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -30);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);
        Date startDate = cal.getTime();

        cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 998);
        Date currentDate = cal.getTime();

        List<Object[]> payments = paymentRepository.calculateTotalPaymentsBySID(startDate, currentDate);

        double totalPayment = 0.0;
        for (Object[] payment : payments) {
            double paymentSum = (Double) payment[1];
            totalPayment += paymentSum;
        }

        return totalPayment;
    }

    public List<Payment> listPayment() {
        return paymentRepository.findAll();
    }

    public void updatePaymentStatus(Long paymentId, int newStatus) {
        Payment payment = paymentRepository.findPaymentById(paymentId).orElse(null);
        if (payment != null) {
            paymentRepository.setPaymentStatusToOne(paymentId.intValue());
            paymentRepository.save(payment);
        }
    }

}

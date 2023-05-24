package com.example.gigagym.services;


import com.example.gigagym.repositories.MemberRepository;
import com.example.gigagym.util.DBConnection;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;



@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public double calculateTotalCharges() {
        double totalCharges = 0.0;
        LocalDate currentDate = LocalDate.now();

        String query = "SELECT charge, startDate FROM members";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                double charge = resultSet.getDouble("charge");
                LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                long durationMonths = (startDate.until(currentDate).getDays()) / 30;
                if (durationMonths == 0) {
                    durationMonths = 1;
                    double rowCharge = charge * durationMonths;
                    totalCharges += rowCharge;
                } else {
                    double rowCharge = charge * durationMonths;
                    totalCharges += rowCharge;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCharges;
    }

    public double calculateLastMonthEarnings() {
        double LastMonthEarnings = 0.0;

        String query = "SELECT SUM(charge) AS total FROM members";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            resultSet.next();
            LastMonthEarnings = resultSet.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return LastMonthEarnings;
    }

    public int getNumberOfUsersRegisteredLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonthStartDate = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastMonthEndDate = currentDate.minusMonths(1).withDayOfMonth(lastMonthStartDate.lengthOfMonth());
        return memberRepository.countByRegistrationDateBetween(lastMonthStartDate, lastMonthEndDate);
    }
}
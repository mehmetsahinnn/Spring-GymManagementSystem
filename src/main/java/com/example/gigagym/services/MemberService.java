package com.example.gigagym.services;

import com.example.gigagym.repositories.MemberRepository;
import com.example.gigagym.util.DBConnection;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


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
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH, -30);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = cal.getTime();

        cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date endDate = cal.getTime();

        return memberRepository.countByStartDateBetween(startDate, endDate);
    }

    public double getMaleRatio() {
        long maleCount = memberRepository.countBySex("male");
        long totalCount = memberRepository.count();

        if (totalCount > 0) {
            return (double) maleCount / totalCount;
        } else {
            return 0.0;
        }
    }

    public double getFemaleRatio() {
        long femaleCount = memberRepository.countBySex("female");
        long totalCount = memberRepository.count();
        if (totalCount > 0) {
            return (double) femaleCount / totalCount;
        } else {
            return 0.0;
        }
    }

}
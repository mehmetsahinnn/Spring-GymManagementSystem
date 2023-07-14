package com.example.gigagym.services;

import com.example.gigagym.repositories.MemberRepository;
import com.example.gigagym.util.DBConnection;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public double calculateTotalCharges() {
        return memberRepository.calculateTotalCharges();
    }

    public double calculateLastMonthEarnings() {
        return memberRepository.sumCharges();
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
            double ratio = (double) maleCount / totalCount;
            BigDecimal rounded = BigDecimal.valueOf(ratio).setScale(3, RoundingMode.DOWN);

            return rounded.doubleValue();
        } else {
            return 0.0;
        }
    }

    public double getFemaleRatio() {
        long femaleCount = memberRepository.countBySex("female");
        long totalCount = memberRepository.count();

        if (totalCount > 0) {
            double ratio = (double) femaleCount / totalCount;
            BigDecimal rounded = BigDecimal.valueOf(ratio).setScale(3, RoundingMode.UP);

            return rounded.doubleValue();
        } else {
            return 0.0;
        }
    }


}
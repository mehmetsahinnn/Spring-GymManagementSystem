package com.example.gigagym.services;

import com.example.gigagym.models.Member;
import com.example.gigagym.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;


@Service
public class MemberService {

    private final MemberRepository memberRepository;
    public static final String MALE = "male";
    public static final String FEMALE = "female";

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
        long maleCount = memberRepository.countBySex(MALE);
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
        long femaleCount = memberRepository.countBySex(FEMALE);
        long totalCount = memberRepository.count();

        if (totalCount > 0) {
            double ratio = (double) femaleCount / totalCount;
            BigDecimal rounded = BigDecimal.valueOf(ratio).setScale(3, RoundingMode.UP);

            return rounded.doubleValue();
        } else {
            return 0.0;
        }
    }

    public Page<Member> findMembersByName(String name, Pageable pageable) {
        Specification<Member> spec = (root, query, cb) -> {
            if (name != null) {
                return cb.like(root.get("name"), "%" + name + "%");
            } else {
                return cb.conjunction();
            }
        };
        return memberRepository.findAll(spec, pageable);
    }

}
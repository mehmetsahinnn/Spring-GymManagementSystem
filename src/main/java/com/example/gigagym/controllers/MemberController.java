package com.example.gigagym.controllers;

import com.example.gigagym.models.Member;
import com.example.gigagym.repositories.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MemberController {



    final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/table")
    public String table(Model model, @PageableDefault(size = 250) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        String JobTitle = (String) session.getAttribute("JobTitle");

        Page<Member> page = memberRepository.findAll(pageable);
        model.addAttribute("page", page);

        if ("Maintenance Guy".equals(JobTitle)  ) {

            return "redirect:/maintenance";
        }
        else {
            return "table";
        }
    }
}


package com.example.gigagym.controllers;

import com.example.gigagym.models.Member;
import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.MemberRepository;
import com.example.gigagym.services.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class TableController {

    private final MemberService memberService;

    final MemberRepository memberRepository;

    public TableController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/table")
    public String table(Model model, @PageableDefault(size = 250) Pageable pageable) {

        Page<Member> page = memberRepository.findAll(pageable);
        model.addAttribute("page", page);

        return "table";
    }


    @RequestMapping("/table-1")
    public String table1() {
        return "table-1";
    }

}


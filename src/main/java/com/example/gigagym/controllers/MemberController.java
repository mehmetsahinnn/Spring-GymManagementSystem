package com.example.gigagym.controllers;

import com.example.gigagym.models.Member;
import com.example.gigagym.repositories.MemberRepository;
import com.example.gigagym.services.EquipmentService;
import com.example.gigagym.services.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
public class MemberController {


    final MemberRepository memberRepository;
    final EquipmentService equipmentService;

    public MemberController(MemberRepository memberRepository, MemberService memberService, EquipmentService equipmentService) {
        this.memberRepository = memberRepository;
        this.equipmentService = equipmentService;
    }

    @GetMapping("/members")
    public String table(Model model, @PageableDefault(size = 250) Pageable pageable, HttpSession session) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        String JobTitle = (String) session.getAttribute("JobTitle");

        Page<Member> page = memberRepository.findMembers(pageable);
        model.addAttribute("page", page);

        if ("Maintenance Guy".equals(JobTitle)) {

            return "redirect:/maintenance";
        } else {
            return "members";
        }
    }

    @PostMapping("/members")
    public String addMember(
            @RequestParam("name") String name,
            @RequestParam("emailAddress") String emailAddress,
            @RequestParam("sex") String sex,
            @RequestParam("address") String address,
            @RequestParam("charge") double charge,
            @RequestParam("ptId") int ptID,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Member member = new Member();
        member.setName(name);
        member.setEmailAddress(emailAddress);
        member.setSex(sex);
        member.setAddress(address);
        member.setCharge(charge);
        member.setPtId(ptID);
        member.setStartDate(startDate);
        member.setEndDate(endDate);
        memberRepository.save(member);
        return "redirect:/home";
    }

    @PostMapping("/updateMember")
    public String updateTable(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("emailAddress") String emailAddress,
                              @RequestParam("sex") String sex,
                              @RequestParam("address") String address,
                              @RequestParam("charge") double charge,
                              @RequestParam("ptId") int ptId,
                              @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                              @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        memberRepository.updateMember(id, name, emailAddress, sex, address, charge, ptId, startDate, endDate);
        return "redirect:/members";
    }

    @DeleteMapping("/deleteMember/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Integer id){
        memberRepository.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/topSearchMember")
    public ModelAndView search(@RequestParam("page") String pageName) {
        if (equipmentService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/members");
        }
    }
}


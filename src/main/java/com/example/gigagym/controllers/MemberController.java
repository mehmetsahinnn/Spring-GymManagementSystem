package com.example.gigagym.controllers;

import com.example.gigagym.models.Member;
import com.example.gigagym.repositories.MemberRepository;
import com.example.gigagym.services.EquipmentService;
import com.example.gigagym.services.MemberService;
import com.example.gigagym.util.UtilityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
public class MemberController {
    final MemberRepository memberRepository;
    final EquipmentService equipmentService;
    final MemberService memberService;
    final UtilityService utilityService;

    public MemberController(MemberRepository memberRepository, MemberService memberService, EquipmentService equipmentService, UtilityService utilityService) {
        this.memberRepository = memberRepository;
        this.equipmentService = equipmentService;
        this.memberService = memberService;
        this.utilityService = utilityService;
    }

    @GetMapping("/members")
    public Object table(Model model, @PageableDefault(size = 250) Pageable pageable, HttpSession session, @RequestParam(required = false) String name, HttpServletRequest request) {
        String StaffName = (String) session.getAttribute("StaffName");
        model.addAttribute("StaffName", StaffName);
        String JobTitle = (String) session.getAttribute("JobTitle");

        Page<Member> page = memberService.findMembersByName(name, pageable);
        model.addAttribute("page", page);


        String requestedWithHeader = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWithHeader)) {
            return ResponseEntity.ok(page);
        } else {
            if ("Maintenance Guy".equals(JobTitle)) {
                return "redirect:/maintenance";
            } else {
                return "members";
            }
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
    public ResponseEntity<?> deleteMember(@PathVariable Integer id) {
        memberRepository.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/topSearchMember")
    public ModelAndView search(@RequestParam("page") String pageName) {
        if (utilityService.pageExists(pageName)) {
            return new ModelAndView("redirect:/" + pageName);
        } else {
            return new ModelAndView("redirect:/members");
        }
    }

    @RequestMapping("/searchMember/{name}")
    public ModelAndView searchMember(@PathVariable String name) {
        if (name == null) {
            return new ModelAndView("redirect:/members");
        }
        return new ModelAndView("redirect:/members?name=" + name);
    }

}


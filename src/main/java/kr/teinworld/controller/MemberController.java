package kr.teinworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping(value = "/members/signUp")
    public String signUp(){
        return "/members/signUp";
    }

    @GetMapping(value = "/members/new")
    public String createMember(){
        return "/members/createMemberForm";
    }



}

package kr.teinworld.controller;

import kr.teinworld.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/signUp")
    public String signUp(){
        return "/members/signUp";
    }

    @GetMapping(value = "/members/new")
    public String createMember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String createMember(@Valid MemberForm memberForm, BindingResult result) {
        //BindingResult -> name field에 대해 에러를 뽑아준다.
        if(result.hasErrors()){
            return "/members/createMemberForm";
        }

        memberService.save(memberForm);

        return "/members/signUp";
    }



}

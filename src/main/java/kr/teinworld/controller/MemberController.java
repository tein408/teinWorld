package kr.teinworld.controller;

import kr.teinworld.domain.Member;
import kr.teinworld.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @ResponseBody
    @PostMapping(value = "members/dupEmail")
    public String dupEmail (@RequestBody MemberForm memberForm) {
        String message;
        Optional<Member> findEmail = memberService.findByEmail(memberForm.getEmail());
        if(findEmail.isPresent()) {
            message = "이미 사용중인 이메일";
        } else {
            message = "사용 가능한 이메일";
        }
        return message;
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

    @GetMapping(value = "/members/memberList")
    public String memberList(Model model, @AuthenticationPrincipal Member member) {
        String memberAuth = member.getAuth();
        if (memberAuth.equals("ROLE_ADMIN")) {
            model.addAttribute("memberList", memberService.findMembers());
        }
        return "/members/membersList";
    }


}

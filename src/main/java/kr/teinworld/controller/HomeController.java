package kr.teinworld.controller;

import kr.teinworld.domain.Member;
import kr.teinworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal Member member, Model model){
        if(member != null){
            Member findMember = memberRepository.findOne(member.getId());
            if(findMember != null) {
                model.addAttribute("member", findMember);
            }
        }
        return "home";
    }

}

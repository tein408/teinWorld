package kr.teinworld.service;

import kr.teinworld.controller.MemberForm;
import kr.teinworld.domain.Member;
import kr.teinworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberForm memberForm){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberForm.setPwd(encoder.encode(memberForm.getPwd()));
        memberForm.setAuth("USER");

        Member member = new Member(memberForm.getEmail(), memberForm.getName(), memberForm.getPwd(), memberForm.getAuth());

        return memberRepository.save(member);
    }

}

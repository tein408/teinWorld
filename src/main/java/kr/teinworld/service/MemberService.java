package kr.teinworld.service;

import kr.teinworld.controller.MemberForm;
import kr.teinworld.domain.Member;
import kr.teinworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        String pwd = encoder.encode(memberForm.getPwd());
        String auth = "USER";

        Member member = new Member(memberForm.getEmail(), memberForm.getName(), pwd, auth);

        return memberRepository.save(member);
    }

}

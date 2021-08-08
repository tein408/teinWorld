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
public class MemberService implements UserDetailsService {
//DB에서 유저 정보를 직접 가져오는 인터페이스를 구현

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberForm memberForm){
        validateDuplicateMember(memberForm);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode(memberForm.getPwd());
        String auth = "USER";

        Member member = new Member(memberForm.getEmail(), memberForm.getName(), pwd, auth);

        return memberRepository.save(member);
    }

    public void validateDuplicateMember(MemberForm memberForm) {
        //이메일 검색시 결과 존재 == 이미 존재하는 이메일
        memberRepository.findByEmail(memberForm.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일");
                });
    }

    //param 에 따라 회원 정보 검색하여 반환
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email).get();
    }

    @Transactional
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    @Transactional
    public Member findById(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}

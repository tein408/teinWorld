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

import java.util.List;
import java.util.Optional;

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
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public Member findById(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member memberUpdate(MemberForm memberForm) {
        //변경 감지 기능을 사용하여 준영속 엔티티 수정
        //영속성 컨텍스트에서 엔티티 조회 -> 데이터 수정
        //트랜잭션 내에서 엔티티 재조회, 값 변경 -> 트랜잭션 커밋 시점에 dirty checking 발생
        //-> DB에 update query 실행

        Optional<Member> member = memberRepository.findByEmail(memberForm.getEmail());
        Long memberId = member.get().getId();
        Member findMember = findById(memberId);

        String pwd = memberForm.getPwd();
        if(pwd.length() != 0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            pwd = encoder.encode(memberForm.getPwd());
        } else {
            pwd = member.get().getPwd();
        }

        findMember.change(memberForm.getName(), pwd);
        return findMember;
    }

    @Transactional
    public void memberRemove(Long id) {
        Member findMember = memberRepository.findOne(id);
        if(findMember != null) {
            memberRepository.memberRemove(findMember);
        }
    }

}

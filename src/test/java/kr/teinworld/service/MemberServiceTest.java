package kr.teinworld.service;

import kr.teinworld.controller.MemberForm;
import kr.teinworld.domain.Member;
import kr.teinworld.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void saveMember() throws Exception{
        //given
        MemberForm member = new MemberForm("kim", "testEmail@email.com", "password", "USER");

        //when
        Long saveId = memberService.save(member);

        //then
        assertEquals(member.getName(), memberRepository.findOne(saveId).getName());
    }

    @Test
    public void duplicateMemberException() throws Exception {
        //given
        MemberForm member1 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");
        MemberForm member2 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");

        //when
        memberService.save(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.save(member2));

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일");
    }

    @Test
    public void findByEmail() throws Exception{
        //given
        MemberForm member1 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");
        MemberForm member2 = new MemberForm("kim2", "testEmail2@email.com", "password2", "USER");

        //when
        memberService.save(member1);
        memberService.save(member2);
        String email = "testEmail1@email.com";

        //then
        assertEquals(member1.getEmail(), memberRepository.findByEmail(email).get().getEmail());
    }

    @Test
    public void findById() throws Exception{
        //given
        MemberForm member1 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");
        MemberForm member2 = new MemberForm("kim2", "testEmail2@email.com", "password2", "USER");

        //when
        memberService.save(member1);
        memberService.save(member2);

        //then
        assertEquals("kim1", memberService.findById(1L).getName());
    }

    @Test
    public void findMembers() throws Exception {
        //given
        MemberForm member1 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");
        MemberForm member2 = new MemberForm("kim2", "testEmail2@email.com", "password2", "USER");

        //when
        memberService.save(member1);
        memberService.save(member2);

        //then
        assertEquals(2, memberService.findMembers().size());
    }

    @Test
    public void memberUpdate() throws Exception {
        //given
        MemberForm member1 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");
        memberService.save(member1);

        //when
        MemberForm member2 = new MemberForm("kim2", "testEmail1@email.com", "password1", "USER");
        Member changeMember = memberService.memberUpdate(member2);

        //then
        assertEquals("kim2", changeMember.getName());
    }

    @Test
    public void memberRemove() throws Exception {
        //given
        MemberForm member1 = new MemberForm("kim1", "testEmail1@email.com", "password1", "USER");
        Long saveId = memberService.save(member1);

        //when
        memberService.memberRemove(saveId);

        //then
        assertEquals(0, memberService.findMembers().size());
    }

}
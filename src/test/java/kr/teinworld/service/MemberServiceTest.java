package kr.teinworld.service;

import kr.teinworld.controller.MemberForm;
import kr.teinworld.repository.MemberRepository;
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

}
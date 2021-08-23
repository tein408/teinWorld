package kr.teinworld.service;

import kr.teinworld.controller.MemberForm;
import kr.teinworld.domain.Board;
import kr.teinworld.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    @Test
    public void boardSave() throws Exception {
        //given
        MemberForm member = new MemberForm("kim", "testEmail@email.com", "password", "USER");
        Long saveId = memberService.save(member);
        Member findMember = memberService.findById(saveId);
        Board board = new Board("title", "content", findMember, LocalDateTime.now(), 0,0);

        //when
        boardService.boardCreate(board);

        //then
        assertEquals(board.getTitle(), "title");

    }

}
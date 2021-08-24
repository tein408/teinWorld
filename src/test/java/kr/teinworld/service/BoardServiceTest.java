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

    @Test
    public void findAll() throws Exception {
        //given
        MemberForm member = new MemberForm("kim", "testEmail@email.com", "password", "USER");
        Long saveId = memberService.save(member);
        Member findMember = memberService.findById(saveId);
        Board board1 = new Board("title", "content", findMember, LocalDateTime.now(), 0,0);
        Board board2 = new Board("title2", "content2", findMember, LocalDateTime.now(), 0,0);

        //when
        boardService.boardCreate(board1);
        boardService.boardCreate(board2);

        //then
        assertEquals(boardService.findAll().size(), 2);
    }

    @Test
    public void findOne() throws Exception {
        //given
        MemberForm member = new MemberForm("kim", "testEmail@email.com", "password", "USER");
        Long saveId = memberService.save(member);
        Member findMember = memberService.findById(saveId);
        Board board1 = new Board("title", "content", findMember, LocalDateTime.now(), 0,0);
        Board board2 = new Board("title2", "content2", findMember, LocalDateTime.now(), 0,0);

        //when
        boardService.boardCreate(board1);
        Long findBoardId = boardService.boardCreate(board2);

        //then
        assertEquals(boardService.findOne(findBoardId).getTitle(), "title2");
    }

    @Test
    public void test() throws Exception {
        //given
        MemberForm member = new MemberForm("kim", "testEmail@email.com", "password", "USER");
        Long saveId = memberService.save(member);
        Member findMember = memberService.findById(saveId);
        Board board1 = new Board("title", "content", findMember, LocalDateTime.now(), 0,0);
        Board board2 = new Board("title2", "content2", findMember, LocalDateTime.now(), 0,0);
        Board board3 = new Board("title3", "content3", findMember, LocalDateTime.now(), 0,0);

        //when
        boardService.boardCreate(board1);
        boardService.boardCreate(board2);
        boardService.boardCreate(board3);
        boardService.remove(board1);

        //then
        assertEquals(boardService.findAll().size(), 2);
    }

}
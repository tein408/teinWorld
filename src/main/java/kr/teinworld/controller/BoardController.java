package kr.teinworld.controller;

import kr.teinworld.domain.Board;
import kr.teinworld.domain.Member;
import kr.teinworld.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/boards/write")
    public String createForm(Model model, @AuthenticationPrincipal Member member) {
        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("member", member);
        return "/boards/createBoardForm";
    }

    @PostMapping(value = "/boards/newBoard")
    public String createBoard(BoardForm boardForm, @AuthenticationPrincipal Member member) {
        Board board = new Board(boardForm.getTitle(), boardForm.getContent(), member, LocalDateTime.now(), 0 ,0);
        boardService.boardCreate(board);
        return "/boards/boardList";
    }


}

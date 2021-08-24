package kr.teinworld.controller;

import kr.teinworld.domain.Board;
import kr.teinworld.domain.Member;
import kr.teinworld.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        Long boardId = boardService.boardCreate(board);
        return "redirect:/boards/board/" + boardId;
    }

    @GetMapping(value = "/boards/board")
    public String boardList(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "/boards/boardList";
    }

    @GetMapping(value = "/boards/board/{boardId}")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);
        model.addAttribute("board", board);
        return "/boards/boardDetail";
    }

    @GetMapping(value = "/boards/board/{boardId}/edit")
    public String edit(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);
        model.addAttribute("boardForm", board);
        return "/boards/editBoardForm";
    }

    @PostMapping(value = "/boards/board/{boardId}/edit")
    public String editBoard(@PathVariable("boardId") Long boardId, @ModelAttribute("form") BoardForm boardForm, Model model) {
        Board board = boardService.edit(boardId, boardForm.getTitle(), boardForm.getContent());
        model.addAttribute("boardForm", board);
        return "redirect:/boards/board/{boardId}";
    }

    @GetMapping(value = "/boards/board/{boardId}/delete")
    public String deleteBoard(@PathVariable("boardId") Long boardId) {
        Board board = boardService.findOne(boardId);
        boardService.remove(board);
        return "redirect:/boards/board";
    }

}

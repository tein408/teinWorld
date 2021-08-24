package kr.teinworld.service;

import kr.teinworld.domain.Board;
import kr.teinworld.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long boardCreate(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }

    @Transactional
    public Board edit(Long boardId, String title, String content) {
        Board findBoard = boardRepository.findOne(boardId);
        findBoard.change(title, content);
        return findBoard;
    }

    @Transactional
    public void remove(Board board) {
        boardRepository.remove(board);
    }
}

package kr.teinworld.service;

import kr.teinworld.domain.Board;
import kr.teinworld.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long boardCreate(Board board) {
        return boardRepository.save(board);
    }

}
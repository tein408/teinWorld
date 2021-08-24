package kr.teinworld.repository;

import kr.teinworld.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b order by b.regDate desc", Board.class).getResultList();
    }

    public Board findOne(Long boardId) {
        return em.find(Board.class, boardId);
    }

    public void remove(Board board) {
        em.remove(board);
    }
}

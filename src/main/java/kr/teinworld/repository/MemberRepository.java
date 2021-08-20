package kr.teinworld.repository;

import kr.teinworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public Optional<Member> findByEmail(String email) {
        List<Member> findMember = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email).getResultList();
        return findMember.stream().findAny();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}

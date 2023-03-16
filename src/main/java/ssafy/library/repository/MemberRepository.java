package ssafy.library.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.library.domain.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;


    /**
     * 회원 등록(저장) 및 수정
     * @param member
     */
    public void save(Member member) {
        if(member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member); // id가 있는 경우 수정 => merge()
        }
    }


    /**
     * 회원 전체 조회
     * @return 회원리스트
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    /**
     * 회원 이름으로 조회
     * @param name
     * @return 회원리스트
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name like :name",Member.class)
                .setParameter("name", "%" + name+ "%") // 해당 이름이 포함된 경우 모두 조회하기 위해 like 연산
                .getResultList();
    }

    /*
    회원 삭제 => 회원 아이디를 전송받아서 삭제를 진행
    1. 회원아이디가 존재하지 않는 경우 throws
     */

}

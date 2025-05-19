package com.example.todo_api.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Member member) {
        em.persist(member);
    }

    // 조회
    // 단건 조회 (한 개의 데이터 조회)
    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    // 다건 조회 (여러 개의 데이터 조회)
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }

    // 삭제
    public void deleteById(Long memberId) {
        Member member = findById(memberId);
        em.remove(member);
    }

    // TEST 용도로만 사용!!!
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}

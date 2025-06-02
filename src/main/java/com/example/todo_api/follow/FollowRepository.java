package com.example.todo_api.follow;

import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Follow follow) {
        em.persist(follow);
    }

    // 조회
    // 단건 조회 (한 개의 데이터 조회)
    public Follow findById(Long followId) {
        return em.find(Follow.class, followId);
    }

    // 다건 조회 (여러 개의 데이터 조회)
    public List<Follow> findAll() {
        return em.createQuery("select f from Follow as f", Follow.class).getResultList();
    }

    // 조건 조회 (여러 개의 데이터 중에 조건에 맞는 데이터만 조회)
    // 팔로우 검색
    public List<Follow> findAllByFollowee(Member followee) {
        return em.createQuery("select f from Follow as f where f.followee = :follow_followee", Follow.class)
                .setParameter("follow_followee", followee)
                .getResultList();
    }

    // 팔로워 검색
    public List<Follow> findAllByFollower(Member follower) {
        return em.createQuery("select f from Follow as f where f.follower = :follow_follower", Follow.class)
                .setParameter("follow_follower", follower)
                .getResultList();
    }

    public Follow findByFolloweeAndFollower(Member followee, Member follower) {
        List<Follow> result = em.createQuery("select f from Follow f where f.followee = :followee and f.follower = :follower", Follow.class)
                .setParameter("followee", followee)
                .setParameter("follower", follower)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }


    // 삭제
    public void deleteById(Long followId) {
        Follow follow = findById(followId);
        em.remove(follow);
    }

    // TEST 용도로만 사용!!!
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}

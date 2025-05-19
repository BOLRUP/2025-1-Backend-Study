package com.example.todo_api.follow;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FollowRepositoryTest {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @Transactional
    @Rollback(false)
    void followSaveTest() {

        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Follow follow = new Follow(member1, member2);
        followRepository.save(follow);

        Assertions.assertThat(follow.getId()).isNotNull();
    }


    @Test
    @Transactional
    void followFindByIdTest() {
        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Follow follow = new Follow(member1, member2);
        followRepository.save(follow);

        followRepository.flushAndClear();

        Follow findFollow = followRepository.findById(follow.getId());

        Assertions.assertThat(findFollow.getId()).isEqualTo(follow.getId());
    }


    @Test
    @Transactional
    void followFindAllTest() {
        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        Member member3 = new Member("ccc33333@gmail.com", "ccc33333!");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Follow follow1 = new Follow(member1, member2);
        Follow follow2 = new Follow(member1, member3);
        Follow follow3 = new Follow(member2, member1);
        followRepository.save(follow1);
        followRepository.save(follow2);
        followRepository.save(follow3);

        List<Follow> followList = followRepository.findAll();

        Assertions.assertThat(followList).hasSize(3);
    }

    @Test
    @Transactional
    void followFindByFolloweeTest() {
        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        Member member3 = new Member("ccc33333@gmail.com", "ccc33333!");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Follow follow1 = new Follow(member1, member2);
        Follow follow2 = new Follow(member1, member3);
        Follow follow3 = new Follow(member2, member1);
        followRepository.save(follow1);
        followRepository.save(follow2);
        followRepository.save(follow3);

        followRepository.flushAndClear();

        List<Follow> followList = followRepository.findAllByFollowee(member1);

        Assertions.assertThat(followList).hasSize(2);
    }


    @Test
    @Transactional
    void followFindByFollowerTest() {
        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        Member member3 = new Member("ccc33333@gmail.com", "ccc33333!");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Follow follow1 = new Follow(member1, member2);
        Follow follow2 = new Follow(member1, member3);
        Follow follow3 = new Follow(member2, member1);
        followRepository.save(follow1);
        followRepository.save(follow2);
        followRepository.save(follow3);

        followRepository.flushAndClear();

        List<Follow> followList = followRepository.findAllByFollower(member1);

        Assertions.assertThat(followList).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback(false)
    void followDeleteTest() {
        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Follow follow1 = new Follow(member1, member2);
        followRepository.save(follow1);

        followRepository.flushAndClear();

        followRepository.deleteById(follow1.getId());
    }

    @AfterAll
    public static void doNotFinish() {
        System.out.println("test finished");
        while (true) {}
    }
}

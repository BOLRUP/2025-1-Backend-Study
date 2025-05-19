package com.example.todo_api.member;

import com.example.todo_api.todo.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberRepositoryTest {


    @Autowired MemberRepository memberRepository;
    private TodoRepository todoRepository;


    @Test
    @Transactional
    @Rollback(false)
    void memberSaveTest() {

        Member member = new Member("aaa11111@gmail.com", "aaa11111!");
        memberRepository.save(member);

        Assertions.assertThat(member.getId()).isNotNull();
    }


    @Test
    @Transactional
    void memberFindOneByIdTest() {

        Member member = new Member("aaa11111@gmail.com", "aaa11111!");
        memberRepository.save(member);

        memberRepository.flushAndClear();

        Member findMember = memberRepository.findById(member.getId());

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }


    @Test
    @Transactional
    void memberFindAllTest() {
        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        Member member3 = new Member("ccc33333@gmail.com", "ccc33333!");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberList = memberRepository.findAll();

        Assertions.assertThat(memberList).hasSize(3);
    }


    @Test
    @Transactional
    @Rollback(false)
    void memberDeleteTest() {

        Member member1 = new Member("aaa11111@gmail.com", "aaa11111!");
        Member member2 = new Member("bbb22222@gmail.com", "bbb22222!");
        memberRepository.save(member1);
        memberRepository.save(member2);

        memberRepository.flushAndClear();

        memberRepository.deleteById(member1.getId());

        List<Member> memberList = memberRepository.findAll();

        Assertions.assertThat(memberList).hasSize(1);
    }


    @AfterAll
    public static void doNotFinish() {
        System.out.println("test finished");
        while (true) {}
    }
}

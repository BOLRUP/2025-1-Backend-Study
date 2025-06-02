package com.example.todo_api.member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long registerMember(String email, String password) {
        Member member = new Member(email, password);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Long loginMember(String email, String password) {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new RuntimeException("이메일이 존재하지 않습니다.");
        }

        if (!member.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 동일하지 않습니다.");
        }

        return member.getId();
    }
}

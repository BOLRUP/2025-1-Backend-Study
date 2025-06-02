package com.example.todo_api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody MemberRegisterRequest requset) {
        memberService.registerMember(requset.getEmail(), requset.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody MemberLoginRequest requset) {
        Long memberId = memberService.loginMember(requset.getEmail(), requset.getPassword());
        return ResponseEntity.ok(memberId);
    }
}

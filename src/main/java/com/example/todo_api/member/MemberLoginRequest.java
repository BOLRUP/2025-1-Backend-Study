package com.example.todo_api.member;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    String email;
    String password;
}

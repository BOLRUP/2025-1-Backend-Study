package com.example.todo_api.todo;

import lombok.Getter;

@Getter
public class TodoUpdateRequest {
    private String newContent;
    private Long memberId;
}

package com.example.todo_api.follow;

import lombok.Getter;

@Getter
public class FollowRequest {
    Long followerId;
    Long followeeId;
}

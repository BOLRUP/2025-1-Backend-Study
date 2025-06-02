package com.example.todo_api.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<Void> follow(@RequestBody FollowRequest request) {
        followService.follow(request.getFollowerId(), request.getFolloweeId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friend/{followeeId}")
    public ResponseEntity<List> getFollowers(@PathVariable Long followeeId) {
        List<Follow> followers = followService.getFollowers(followeeId);
        return ResponseEntity.ok(followers);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollow(@RequestBody FollowRequest request) {
        followService.unfollow(request.getFollowerId(), request.getFolloweeId());
        return ResponseEntity.noContent().build();
    }
}

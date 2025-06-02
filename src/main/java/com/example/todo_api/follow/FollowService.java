package com.example.todo_api.follow;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long follow(Long followerId, Long followeeId) {
        Member followee = memberRepository.findById(followeeId);

        if (followee == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        Member follower = memberRepository.findById(followerId);

        if (follower == null) {
            throw new RuntimeException("팔로워가 존재하지 않습니다.");
        }

        Follow checkFollow = followRepository.findByFolloweeAndFollower(followee, follower);
        if (checkFollow != null) {
            throw new RuntimeException("이미 팔로우 중입니다.");
        }

        Follow follow = new Follow(followee, follower);
        followRepository.save(follow);
        return follow.getId();
    }

    @Transactional
    public void unfollow(Long followerId, Long followeeId) {
        Member followee = memberRepository.findById(followeeId);

        if (followee == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        Member follower = memberRepository.findById(followerId);

        if (follower == null) {
            throw new RuntimeException("팔로워가 존재하지 않습니다.");
        }

        Follow follow = followRepository.findByFolloweeAndFollower(followee, follower);

        if (follow == null) {
            throw new RuntimeException("팔로우 관계가 존재하지 않습니다.");
        }

        followRepository.deleteById(follow.getId());
    }

    @Transactional
    public List<Follow> getFollowers(Long followeeId) {
        Member followee = memberRepository.findById(followeeId);

        if (followee == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }

        return followRepository.findAllByFollowee(followee);
    }
}

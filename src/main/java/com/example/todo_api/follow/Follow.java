package com.example.todo_api.follow;

import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", columnDefinition = "bigint")
    private Long id;

    @JoinColumn(name = "followee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followee;

    @JoinColumn(name = "follower_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member follower;


    public Follow(Member followee, Member follower) {
        this.followee = followee;
        this.follower = follower;
    }
}
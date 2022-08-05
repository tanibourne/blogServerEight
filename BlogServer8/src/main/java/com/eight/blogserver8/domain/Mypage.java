package com.eight.blogserver8.domain;

import org.springframework.security.core.parameters.P;

import javax.persistence.*;

@Entity
public class Mypage {

    @Id
    @Column(name="mypage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "comment_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

//    @JoinColumn(name = "subcomment_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Subcomment subcomment;
//
//    @JoinColumn(name = "like_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Like like;
//



}

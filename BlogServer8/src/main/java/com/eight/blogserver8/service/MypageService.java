package com.eight.blogserver8.service;

import com.eight.blogserver8.controller.request.MypageRequestDto;
import com.eight.blogserver8.controller.response.MypageResponseDto;
import com.eight.blogserver8.controller.response.ResponseDto;
import com.eight.blogserver8.domain.Comment;
import com.eight.blogserver8.domain.Member;
import com.eight.blogserver8.domain.Mypage;
import com.eight.blogserver8.domain.Post;
import com.eight.blogserver8.jwt.TokenProvider;
import com.eight.blogserver8.repository.CommentRepository;
import com.eight.blogserver8.repository.MemberRepository;
import com.eight.blogserver8.repository.MypageRepository;
import com.eight.blogserver8.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {


    private final MypageRepository mypageRepository;
    //
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
//    private final LikeRepository likeRepository;
//    private final SubCommentRepository subCommentRepository;
    private final TokenProvider tokenProvider;



    public ResponseDto<?> viewMypage(MypageRequestDto mypageRequestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

//        String name = maybePerson.get().getName();
        Member findMember = memberRepository.findByNickname(mypageRequestDto.getNickname()).get();

        List<Post> post = postRepository.findByMember(findMember);
        List<Comment> comment = commentRepository.findByMember(findMember);


//         mypageRepository.save(mypageList);

         return ResponseDto.success(
                 MypageResponseDto.builder()
                         .postList(post)
                         .commentList(comment)
                         //넣을에정 좋아요, 대댓글, 이미지..
                         .build()

         );

    }








    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }


}

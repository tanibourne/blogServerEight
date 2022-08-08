package com.eight.blogserver8.service;

import com.eight.blogserver8.controller.request.MypageRequestDto;
import com.eight.blogserver8.controller.response.*;
import com.eight.blogserver8.domain.*;
import com.eight.blogserver8.jwt.TokenProvider;
import com.eight.blogserver8.repository.*;
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
    private final SubCommentRepository subCommentRepository;
    //    private final LikeRepository likeRepository;
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

        List<Post> postList = postRepository.findByMember(findMember);
        List<Comment> commentList = commentRepository.findByMember(findMember);
        List<SubComment> subCommentList = subCommentRepository.findByMember(findMember);

        Mypage mypage = new Mypage();
        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        List<MypagePostResponseDto> postListDto = new ArrayList<>();
        List<MypageCommentResponseDto> commentListDto = new ArrayList<>();
        List<MypageSubCommentResponseDto> subcommentListDto = new ArrayList<>();

        for( Post post : postList){
            postListDto.add(
                    MypagePostResponseDto.builder()
                            .postId(post.getId())
                            .title(post.getTitle())
                            .postContent(post.getContent())
                            .build()
            );
        }


        for (Comment comment : commentList){
            commentListDto.add(
                    MypageCommentResponseDto.builder()
                            .commentId(comment.getId())
                            .commentContent(comment.getContent())
                            .build()
            );
        }

        for(SubComment subComment : subCommentList){
            subcommentListDto.add(
                    MypageSubCommentResponseDto.builder()
                            .subCommentId(subComment.getId())
                            .subCommentContent(subComment.getContent())
                            .build()
            );
        }

        mypage.update(findMember,postList,commentList,subCommentList);// 마이페이지 객체에 업데이트
        mypageRepository.save(mypage);
        mypageResponseDto.update(postListDto,commentListDto,subcommentListDto ); // 빌더 안쓰고 리스폰 만듬.

         return ResponseDto.success( mypageResponseDto );

    }








    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }


}

package com.eight.blogserver8.controller.response;

import com.eight.blogserver8.domain.Comment;
import com.eight.blogserver8.domain.Post;
import com.eight.blogserver8.domain.SubComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MypageResponseDto {

    private List postList;
    private List commentList;
    private List subCommentList;

    public void update(
            List<MypagePostResponseDto> postListDto,
            List<MypageCommentResponseDto> commentListDto,
            List<MypageSubCommentResponseDto> subcommentListDto){
        this.postList = postListDto;
        this.commentList = commentListDto;
        this.subCommentList = subcommentListDto;

    }

}

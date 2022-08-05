package com.eight.blogserver8.controller.response;

import com.eight.blogserver8.domain.Comment;
import com.eight.blogserver8.domain.Post;
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
    private List<Post> postList;
    private List<Comment> commentList;

}

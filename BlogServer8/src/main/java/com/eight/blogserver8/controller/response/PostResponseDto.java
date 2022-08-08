package com.eight.blogserver8.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
  private Long id;
  private String title;
  private String content;
  private String author;
  private Long heart;
  private Long heartCount;
  private List<CommentResponseDto> commentResponseDtoList;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  //게시글 목록 response에 id, 제목, 작성자, 좋아요 개수, 대댓글 제외한 댓글 개수, 등록일, 수정일 나타내기

}

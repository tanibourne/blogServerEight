package com.eight.blogserver8.controller.request;

import com.eight.blogserver8.controller.response.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

  private Long id;
  private String title;
  private String content;
  private String author;
  private int heartCount;
  private List<CommentResponseDto> commentResponseDtoList;
  private LocalDateTime createAt;
  private LocalDateTime modifiedAt;



}

package com.eight.blogserver8.repository;

import com.eight.blogserver8.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByModifiedAtDesc();

  @Override
  Optional<Post> findById(Long aLong);
}

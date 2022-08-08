package com.eight.blogserver8.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nickname;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  // cascade = CascadeType.ALL=> member가 삭제될 때 연관된 엔티티인 Heart도 같이 삭제
  @OneToMany(
          mappedBy = "member",
          cascade = CascadeType.ALL,
          orphanRemoval = true,
          fetch = FetchType.LAZY)
  private List<HeartPost> heartPosts;

  @OneToMany(
          mappedBy = "member",
          cascade = CascadeType.ALL,
          orphanRemoval = true,
          fetch = FetchType.LAZY)
  private List<HeartComment> heartComments;

  @OneToMany(
          mappedBy = "member",
          cascade = CascadeType.ALL,
          orphanRemoval = true,
          fetch = FetchType.LAZY)
  private List<HeartSubComment> heartSubComments;




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Member member = (Member) o;
    return id != null && Objects.equals(id, member.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public boolean validatePassword(PasswordEncoder passwordEncoder, String password) {
    return passwordEncoder.matches(password, this.password);
  }
}

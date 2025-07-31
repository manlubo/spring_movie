package com.gitbaby.mreview.domain.entity;

import com.gitbaby.mreview.domain.dto.MovieImageDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;

  private String title;


  // private List<MovieImageDTO> list; 이게 없어서 캐스팅 ㅡㅡ
}

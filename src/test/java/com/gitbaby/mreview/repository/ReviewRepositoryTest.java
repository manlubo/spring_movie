package com.gitbaby.mreview.repository;

import com.gitbaby.mreview.domain.entity.Member;
import com.gitbaby.mreview.domain.entity.Movie;
import com.gitbaby.mreview.domain.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {
  @Autowired
  private ReviewRepository repository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    log.info(repository);
  }

  @Test
  @Transactional
  @DisplayName("리뷰 작성")
  public void testInsert(){
    IntStream.rangeClosed(1, 200).forEach(i -> {
      Long mno = new Random().nextLong(100) + 1;
      Long mid = new Random().nextLong(100) + 1;
      Member member = Member.builder().mid(mid).build();

      Review review = Review.builder()
              .member(member)
              .movie(Movie.builder().mno(mno).build())
              .grade(new Random().nextInt(5) + 1)
              .text("이 영화에 대한 느낌" + i)
              .build();
        repository.save(review);
    });
  }

  @Test
  @DisplayName("영화 번호로 리뷰 찾기")
  public void testFindByMovieMno(){
    repository.findByMovie_mno(1L).forEach(r -> {
      log.info(r);
      log.info(r.getMember().getEmail());
//      log.info(r.getMovie().getTitle());
    });
  }


}

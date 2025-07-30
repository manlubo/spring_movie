package com.gitbaby.mreview.repository;

import com.gitbaby.mreview.domain.entity.Movie;
import com.gitbaby.mreview.domain.entity.MovieImage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTest {
  @Autowired
  private MovieRepository repository;
  @Autowired
  private MovieImageRepository movieImageRepository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    log.info(repository);
  }

  @Test
  @DisplayName("영화 생성 테스트")
  @Transactional
  public void testInsert(){
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Movie movie = Movie.builder()
              .title("Movie...." + i)
              .build();
      repository.save(movie);

      int count = (int) (Math.random() * 5) + 1;

      for (int j = 0; j < count; j++) {
        MovieImage movieImage = MovieImage.builder()
                .uuid(UUID.randomUUID().toString())
                .movie(movie)
                .imgName("test" + j + ".jpg")
                .build();
        movieImageRepository.save(movieImage);
      }
    });
  }

  @Test
  @DisplayName("영화 목록 조회")
  public void testGetListPage(){
    Page<Object[]> result = repository.getListPage(PageRequest.of(0 , 10, Sort.by(Sort.Direction.DESC, "mno")));
    result.forEach(i -> {log.info(Arrays.toString(i));});
  }


  @Test
  @DisplayName("영화 단일 조회")
  public void testGetMovieWithAll(){
    repository.getMovieWithAll(99L).forEach(m -> {log.info(Arrays.toString(m));});
  }
}

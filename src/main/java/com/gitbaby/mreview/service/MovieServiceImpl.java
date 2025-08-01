package com.gitbaby.mreview.service;

import com.gitbaby.mreview.domain.dto.MovieDTO;
import com.gitbaby.mreview.domain.dto.PageRequestDTO;
import com.gitbaby.mreview.domain.dto.PageResponseDTO;
import com.gitbaby.mreview.domain.entity.Movie;
import com.gitbaby.mreview.domain.entity.MovieImage;
import com.gitbaby.mreview.repository.MovieImageRepository;
import com.gitbaby.mreview.repository.MovieRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Data
public non-sealed class MovieServiceImpl implements MovieService{
  private final MovieRepository movieRepository;
  private final MovieImageRepository movieImageRepository;

  @Override
  @Transactional
  public Long register(MovieDTO movieDTO) {
    Map<String, Object> map = toEntity(movieDTO);
    Movie movie = (Movie) map.get("movie");
    movieRepository.save(movie);
    List<MovieImage> list = ((List<MovieImage>)map.get("images"));
    list.forEach(image -> movieImageRepository.save(image));
    return movie.getMno();
  }

  @Override
  public PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
    return new PageResponseDTO<>(movieRepository.getListPage(pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "mno"))),
            arr-> toDTO((Movie) arr[0],
                    (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])),
                    (Double) arr[2],
                    (Long) arr[3]));
  }

  @Override
  public MovieDTO get(Long mno) {
    List<Object[]> list = movieRepository.getMovieWithAll(mno);
    Movie movie = (Movie) list.get(0) [0];
    List<MovieImage> movieImages = (Arrays.asList((MovieImage) list.getFirst()[1]));


//    List<MovieImage> movieImages = new ArrayList<>();
//    list.forEach(arr -> {
//      MovieImage movieImage = (MovieImage) arr[1];
//      movieImageList.add(movieImage);
//    });
//
    Double avg = (Double) list.get(0)[2];
    Long reviewCnt = (Long) list.get(0)[3];
//
    return toDTO(movie, movieImages, avg, reviewCnt);
  }
}

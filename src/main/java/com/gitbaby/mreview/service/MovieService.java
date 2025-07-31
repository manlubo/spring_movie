package com.gitbaby.mreview.service;

import com.gitbaby.mreview.domain.dto.MovieDTO;
import com.gitbaby.mreview.domain.dto.MovieImageDTO;
import com.gitbaby.mreview.domain.dto.PageRequestDTO;
import com.gitbaby.mreview.domain.dto.PageResponseDTO;
import com.gitbaby.mreview.domain.entity.Movie;
import com.gitbaby.mreview.domain.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 확장을 봉인
public sealed interface MovieService permits MovieServiceImpl {
  Long register(MovieDTO movieDTO);
  PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO);


  default Map<String, Object> toEntity(MovieDTO movieDTO) {
    Map<String, Object> map = new HashMap<>();
    Movie movie =  Movie.builder().title(movieDTO.getTitle()).mno(movieDTO.getMno()).build();
    map.put("movie", movie);
    map.put("images", movieDTO.getList().stream().map(dto -> MovieImage.builder()
            .movie(movie)
            .uuid(dto.getUuid())
            .path(dto.getPath())
            .imgName(dto.getOrigin())
            .build()).toList());
    return map;
  }

  default MovieDTO toDTO(Movie movie, List<MovieImage> images, double avg, long reviewCnt) {
    return MovieDTO.builder()
            .mno(movie.getMno())
            .title(movie.getTitle())
            .regDate(movie.getRegDate())
            .modDate(movie.getModDate())
            .list(images.stream().map(i -> i == null ? null : MovieImageDTO.builder()
                    .origin(i.getImgName())
                    .uuid(i.getUuid())
                    .path(i.getPath())
                    .build()).toList())
            .avg(avg)
            .reviewCnt(reviewCnt)
            .build();
  }
}

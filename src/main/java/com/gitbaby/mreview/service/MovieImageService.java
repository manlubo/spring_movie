package com.gitbaby.mreview.service;

import com.gitbaby.mreview.domain.dto.MovieImageDTO;
import com.gitbaby.mreview.domain.entity.Movie;
import com.gitbaby.mreview.domain.entity.MovieImage;

public interface MovieImageService {
  default MovieImage toEntity(MovieImageDTO movieDTO) {
    return MovieImage.builder()
            .movie(Movie.builder().mno(movieDTO.getMno()).build())
            .uuid(movieDTO.getUuid())
            .path(movieDTO.getPath())
            .imgName(movieDTO.getOrigin())
            .build();
  }
}

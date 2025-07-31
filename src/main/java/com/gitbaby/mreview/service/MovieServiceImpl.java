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

}

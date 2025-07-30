package com.gitbaby.mreview.repository;

import com.gitbaby.mreview.domain.entity.Movie;
import com.gitbaby.mreview.domain.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}

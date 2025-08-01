package com.gitbaby.mreview.service;

import com.gitbaby.mreview.domain.dto.ReviewDTO;
import com.gitbaby.mreview.domain.entity.Review;
import com.gitbaby.mreview.repository.ReviewRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public non-sealed class ReviewServiceImpl implements ReviewService{
  private final ReviewRepository reviewRepository;

  @Override
  public List<ReviewDTO> getListWithMovie(Long mno) {
    return reviewRepository.findByMovie_mno(mno).stream().map(this::toDTO).toList();
  }

  @Override
  public Long register(ReviewDTO dto) {
    return reviewRepository.save(toEntity(dto)).getReviewnum();
  }

  @Override
  public void modify(ReviewDTO dto) {
    Review review = reviewRepository.findById(dto.getReviewnum()).orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));
    review.setGrade(dto.getGrade());
    review.setText(dto.getText());
  }

  @Override
  public void remove(Long reviewnum) {
    reviewRepository.deleteById(reviewnum);
  }
}

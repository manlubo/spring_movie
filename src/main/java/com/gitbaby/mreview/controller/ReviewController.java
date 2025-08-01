package com.gitbaby.mreview.controller;

import com.gitbaby.mreview.domain.dto.ReviewDTO;
import com.gitbaby.mreview.domain.entity.Review;
import com.gitbaby.mreview.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("review")
@Tag(name = "Review API", description = "영화와 관련된 리뷰를 작성할 수 있는 요청 클래스 입니다.")
public class ReviewController {
  private final ReviewService service;

  @Operation(summary = "영화 하나에 작성된 리뷰를 조회하는 호출", description = "필수 파라미터로 영화번호(mno)를 요구합니다.")
  @GetMapping("{mno}/all")
  public ResponseEntity<List<ReviewDTO>> list(@PathVariable Long mno) {
    return ResponseEntity.ok(service.getListWithMovie(mno));
  }

  @PostMapping("{mno}")
  public ResponseEntity<?> add(@RequestBody ReviewDTO dto,  @PathVariable Long mno) {
    return ResponseEntity.ok(service.register(dto));
  }

  @PutMapping("{mno}/{reviewnum}")
  public ResponseEntity<?> update(@RequestBody ReviewDTO dto,  @PathVariable Long mno, @PathVariable Long reviewnum) {
    service.modify(dto);
    return ResponseEntity.ok(dto.getReviewnum());
  }

  @DeleteMapping("{mno}/{reviewnum}")
  public ResponseEntity<?> delete(@PathVariable Long mno, @PathVariable Long reviewnum) {
    service.remove(reviewnum);
    return ResponseEntity.ok(reviewnum);
  }

}

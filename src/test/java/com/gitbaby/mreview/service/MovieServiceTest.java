package com.gitbaby.mreview.service;

import com.gitbaby.mreview.domain.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MovieServiceTest {
  @Autowired
  private MovieService movieService;

  @Test
  @DisplayName("리스트 페이지 가져오기")
  public void testList() {
    movieService.getList(PageRequestDTO.builder().build()).getList().forEach(log::info);
  }
}

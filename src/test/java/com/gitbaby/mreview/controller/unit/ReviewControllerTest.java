package com.gitbaby.mreview.controller.unit;

import com.gitbaby.mreview.controller.ReviewController;
import com.gitbaby.mreview.repository.ReviewRepository;
import com.gitbaby.mreview.service.ReviewService;
import com.gitbaby.mreview.service.ReviewServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ReviewController.class)
@ContextConfiguration(name = "application.yml")
public class ReviewControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ReviewServiceImpl service;
  @MockitoBean
  private ReviewRepository repository;

  @Test
  @DisplayName("단순 목록 조회")
  public void testList() throws Exception {
    Long mno = 20L;
    mockMvc.perform(MockMvcRequestBuilders.get(String.format("/reviews/%d/all", mno)));
  }
}

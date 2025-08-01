package com.gitbaby.mreview.controller;

import com.gitbaby.mreview.domain.dto.MovieDTO;
import com.gitbaby.mreview.domain.dto.PageRequestDTO;
import com.gitbaby.mreview.service.MovieService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("movie")
@Log4j2
@Data
public class MovieController {
  private final MovieService movieService;

  @GetMapping("register")
  public void register() {}


  @PostMapping("register")
  public String register(MovieDTO movieDTO, RedirectAttributes rttr) {
    rttr.addFlashAttribute("msg", movieService.register(movieDTO));
    return "redirect:/movie/register";
  }

  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO, Model model) {
    model.addAttribute("movies", movieService.getList(pageRequestDTO));

  }

  @GetMapping("read")
  public void read(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO, Model model, Long mno) {
    model.addAttribute("dto", movieService.get(mno));
  }

}

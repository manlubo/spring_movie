package com.gitbaby.mreview.domain.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Getter
@ToString
public class PageResponseDTO<DTO, Entity> {
  private List<DTO> list;

  private int totalPage, page, size, start, end;
  private boolean prev, next;
  private List<Integer> pageList;



  public PageResponseDTO(Page<Entity> page, Function<Entity, DTO> mapper) {
    this.list = page.stream().map(mapper).toList();
    this.totalPage = page.getTotalPages();
    makePageList(page.getPageable());
  }

  private void makePageList(Pageable pageable){
    final int PAGE_VIEW_COUNT = 5;
    page = pageable.getPageNumber() + 1;
    size = pageable.getPageSize();

    int tempEnd = (int)(Math.ceil(page / 1d / PAGE_VIEW_COUNT)) * PAGE_VIEW_COUNT;
    start = tempEnd - (PAGE_VIEW_COUNT - 1);
    prev = start > 1;
    end = Math.min(totalPage, tempEnd);
    next = totalPage > tempEnd;

    pageList = IntStream.rangeClosed(start, end).boxed().toList();
  }

}

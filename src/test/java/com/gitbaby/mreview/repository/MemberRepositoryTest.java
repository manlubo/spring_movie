package com.gitbaby.mreview.repository;

import com.gitbaby.mreview.domain.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {
  @Autowired
  private MemberRepository repository;
  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    log.info(repository);
  }

  @Test
  @DisplayName("멤버 생성 테스트")
  @Transactional
  public void testInsert(){
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder()
              .email("r" + i + "@zerock.org")
              .pw("1111")
              .nickname("reviewer" + i)
              .build();
      repository.save(member);
    });
  }

  @Test
  @DisplayName("멤버 pk로 리뷰 삭제")
  @Transactional
  @Commit
  public void testDeleteByMemberMid(){
//    reviewRepository.deleteByMember_mid(11L);
    repository.deleteById(12L);
  }

}

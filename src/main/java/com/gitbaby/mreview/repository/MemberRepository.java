package com.gitbaby.mreview.repository;

import com.gitbaby.mreview.domain.entity.Member;
import com.gitbaby.mreview.domain.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

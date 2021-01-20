package com.gimganghwi.mreview.repository;

import com.gimganghwi.mreview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

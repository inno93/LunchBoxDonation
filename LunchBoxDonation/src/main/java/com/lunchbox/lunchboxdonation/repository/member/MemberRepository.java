package com.lunchbox.lunchboxdonation.repository.member;

import com.lunchbox.lunchboxdonation.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}

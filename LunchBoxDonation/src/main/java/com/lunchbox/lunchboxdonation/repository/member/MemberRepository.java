package com.lunchbox.lunchboxdonation.repository.member;

import com.lunchbox.lunchboxdonation.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberId(String memberId);
    @Query("select m from Member m where m.memberEmail =:email")
    Optional<Member> findByEmail(String email);
}

package com.lunchbox.lunchboxdonation.repository.member;

import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.member.MemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDSL {
    Page<MemberDTO> memberList(Pageable pageable, MemberSearch memberSearch);
}

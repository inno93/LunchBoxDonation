package com.lunchbox.lunchboxdonation.service.member;

import com.lunchbox.lunchboxdonation.domain.OAuth.OAuthAttributes;
import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.entity.member.MemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    public void save(MemberDTO memberDTO);
    public MemberDTO login(MemberDTO memberDTO);
    public List<MemberDTO> findAll();
    public MemberDTO findById(Long id);
    public MemberDTO updateForm(String myId);
    public void update(MemberDTO memberDTO);
    public void deleteById(Long id);
    public String idCheck(String memberId);
    public Page<MemberDTO> MemberList(Pageable pageable, MemberSearch memberSearch);

    public Member saveOrUpdate(OAuthAttributes authAttributes);


}

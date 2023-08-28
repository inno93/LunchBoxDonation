package com.lunchbox.lunchboxdonation.service.member;

import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.entity.member.MemberSearch;
import com.lunchbox.lunchboxdonation.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO){
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        Member member = Member.toMember(memberDTO);
        memberRepository.save(member);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO){
         /*
            1. 회원이 입력한 아이디로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */

        Optional<Member> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (byMemberId.isPresent()){

            Member member = byMemberId.get();
            if (member.getMemberPw().equals(memberDTO.getMemberPw())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(member);
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 아이디 가진 회원이 없다)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member: memberList) {
            memberDTOList.add(MemberDTO.toMemberDTO(member));

        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {

            return MemberDTO.toMemberDTO(optionalMember.get());
        } else {
            return null;
        }

    }

    public MemberDTO updateForm(String myId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(myId);
        if (optionalMember.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMember.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(Member.toUpdateMember(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String idCheck(String memberId) {
        Optional<Member> byMemberId = memberRepository.findByMemberId(memberId);
        System.out.println("service");
        if (byMemberId.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            System.out.println("service1");
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            System.out.println("service2");
            return "ok";
        }
    }

    public Page<MemberDTO> MemberList(Pageable pageable, MemberSearch memberSearch){
        return memberRepository.memberList(pageable,memberSearch);
    }
}

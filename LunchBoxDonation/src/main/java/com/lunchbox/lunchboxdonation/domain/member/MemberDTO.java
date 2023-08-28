package com.lunchbox.lunchboxdonation.domain.member;

import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private Long id;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private String memberPhoneNumber;
    private int memberPoint;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime regDate;

    public static MemberDTO toMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setMemberPw(member.getMemberPw());
        memberDTO.setMemberName(member.getMemberName());
        memberDTO.setMemberEmail(member.getMemberEmail());
        memberDTO.setMemberPhoneNumber(member.getMemberPhoneNumber());
        memberDTO.setMemberPoint(member.getMemberPoint());
        return memberDTO;
    }
}
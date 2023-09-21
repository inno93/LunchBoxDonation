package com.lunchbox.lunchboxdonation.entity.member;

import com.lunchbox.lunchboxdonation.constant.Role;
import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.*;

import javax.persistence.*;

@Table(name = "TBL_MEMBER")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member extends Timestamp {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false)
    private String memberId;

    @Column(unique = true,nullable = false)
    private String memberPw;

    @Column(unique = true,nullable = false)
    private String memberName;
    private int memberPoint;
    private String memberEmail;
    @Column(unique = true,nullable = false)
    private String memberPhoneNumber;
    @Enumerated(EnumType.STRING)
    private Role memberRole;

    public static Member toMember(MemberDTO memberDTO){
        Member member = new Member();
        member.setMemberId(memberDTO.getMemberId());
        member.setMemberPw(memberDTO.getMemberPw());
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberEmail(memberDTO.getMemberEmail());
        member.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());
        member.setMemberPoint(memberDTO.getMemberPoint());

        return member;
    }

    public static Member toUpdateMember(MemberDTO memberDTO){
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setMemberId(memberDTO.getMemberId());
        member.setMemberPw(memberDTO.getMemberPw());
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberEmail(memberDTO.getMemberEmail());
        member.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());
        member.setMemberPoint(memberDTO.getMemberPoint());

        return member;
    }
    @Builder
    public Member(Long id, String memberId, String memberPw, String memberName, int memberPoint, String memberEmail, String memberPhoneNumber, Role memberRole) {
        this.id = id;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberPoint = memberPoint;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberRole = memberRole;
    }

    public Member update(String memberName, String memberPhoneNumber, String memberEmail ){
        this.setMemberName(memberName);
        this.setMemberPhoneNumber(memberPhoneNumber);
        this.setMemberEmail(memberEmail);
        return this;
    }
}
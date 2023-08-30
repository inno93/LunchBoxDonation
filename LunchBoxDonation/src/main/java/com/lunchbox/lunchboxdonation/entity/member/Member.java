package com.lunchbox.lunchboxdonation.entity.member;

import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.*;

import javax.persistence.*;

@Table(name = "TBL_MEMBER")
@Entity
@Getter
@Setter
@ToString
public class Member extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "member_sequence", allocationSize = 1)
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
//    private LocalDateTime modDate;

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

//    @Enumerated(EnumType.STRING)
//    private Role role; //권한
//
//    public enum Role {
//        MEMBER, ADMIN
//    }

}
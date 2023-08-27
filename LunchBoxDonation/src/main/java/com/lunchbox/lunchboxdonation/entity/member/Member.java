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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String memberId;

    @NonNull
    @Column
    private String memberPw;

    @NonNull
    @Column
    private String memberName;
    private String memberEmail;
    private String memberPhoneNumber;
//    private LocalDateTime modDate;

    public static Member toMember(MemberDTO memberDTO){
        Member member = new Member();
        member.setMemberId(memberDTO.getMemberId());
        member.setMemberPw(memberDTO.getMemberPw());
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberEmail(memberDTO.getMemberEmail());
        member.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());

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

        return member;
    }

//    @Enumerated(EnumType.STRING)
//    private Role role; //권한
//
//    public enum Role {
//        MEMBER, ADMIN
//    }

}
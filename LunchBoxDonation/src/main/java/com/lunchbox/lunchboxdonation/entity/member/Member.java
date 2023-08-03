package com.lunchbox.lunchboxdonation.entity.member;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "TBL_MEMBER")
@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // notNull 이랑 nullable 차이점 ? 여쭈어보기
    @Column(nullable = false, unique = true)
    private String memberId;
    private String memberPw;

    @Column(nullable = false)
    private String memberName;
    private Integer memberPoint;
    private String memberPhoneNumber;
    private String memberEmail;
    private LocalDateTime modDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime regDate;


    @Builder
    public Member(Long id, String memberId, String memberPw, String memberName, Integer memberPoint, String memberPhoneNumber, String memberEmail, Role role) {
        this.id = id;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberPoint = memberPoint;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberEmail = memberEmail;
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    private Role role; //권한

    public enum Role {
        MEMBER, ADMIN
    }

//    public void setModDate(LocalDateTime mod_date){
//        this.mod_date = mod_date;
//    }
//
//    public void setRegDate(LocalDateTime reg_date){
//        this.reg_date = reg_date;
//    }


}
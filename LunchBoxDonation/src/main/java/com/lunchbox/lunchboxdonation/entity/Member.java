package com.lunchbox.lunchboxdonation.entity;

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

    @Column(nullable = false, unique = true)
    private String member_id;
    private String member_pw;

    @Column(nullable = false)
    private String member_name;
    private int member_point;
    private String member_phone_number;
    private String member_email;
    private LocalDateTime mod_date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime reg_date;


    @Builder
    public Member(String member_id, String member_pw, String member_name, int member_point,
                  String member_phone_number,String member_email){

        this.member_id = member_id;
        this.member_pw = member_pw;
        this.member_name = member_name;
        this.member_point = member_point;
        this.member_phone_number = member_phone_number;
        this.member_email = member_email;
    }


    @Enumerated(EnumType.STRING)
    private Role role; //권한

    public enum Role {
        MEMBER, ADMIN
    }

    public void setModDate(LocalDateTime mod_date){
        this.mod_date = mod_date;
    }

    public void setRegDate(LocalDateTime reg_date){
        this.reg_date = reg_date;
    }


}
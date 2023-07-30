package com.lunchbox.lunchboxdonation.entity;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;

@Table(name = "TBL_MEMBER")
@Entity
@Getter @Setter
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


}
package com.lunchbox.lunchboxdonation.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
@Table(name="TBL_INQUIRY")
public class Inquiry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @NotNull private String title;
    @NotNull private String content;
    private String answer;
    private Date answerDate;
    @NotNull private Date regDate;
    private Date modDate;

    @Builder
    public Inquiry(Member memberId, String title, String content, String answer){
        this.member = memberId;
        this.title = title;
        this.content = content;
        this.answer = answer;
    }

}

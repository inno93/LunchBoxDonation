package com.lunchbox.lunchboxdonation.entity.notice;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
@Table(name="TBL_NOTICE")
public class Notice {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull private String title;
    private String content;
    @NotNull private Date regDate;
    private Date modDate;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

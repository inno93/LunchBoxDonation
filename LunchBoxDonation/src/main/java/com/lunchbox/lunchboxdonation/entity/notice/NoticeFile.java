package com.lunchbox.lunchboxdonation.entity.notice;

import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name="TBL_NOTICE_FILE")
public class NoticeFile {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_ID", nullable = false)
    private Notice notice;


    @NotNull private String fileUuid;
    @NotNull private String filePath;
    @NotNull private String fileName;
    @NotNull private String regDate;
    private String mdDate;

    @Builder
    public NoticeFile(Notice notice, String fileUuid, String filePath, String fileName) {
        this.notice = notice;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileName = fileName;
    }


}

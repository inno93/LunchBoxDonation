package com.lunchbox.lunchboxdonation.domain.bargain;


import com.lunchbox.lunchboxdonation.entity.bargain.BargainFile;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@NoArgsConstructor
@Getter @ToString
@Setter
public class BargainDTO {
    private Long id;
    private String title;
    private String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDt;

    private BargainFile bargainFile;

    @Builder
    public BargainDTO(Long id, String title, String content, LocalDateTime startDt, LocalDateTime endDt, BargainFile bargainFile) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDt = startDt;
        this.endDt = endDt;
        this.bargainFile = bargainFile;
    }
}

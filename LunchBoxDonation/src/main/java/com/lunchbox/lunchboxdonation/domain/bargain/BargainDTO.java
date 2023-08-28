package com.lunchbox.lunchboxdonation.domain.bargain;


import com.lunchbox.lunchboxdonation.entity.bargain.BargainFile;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
public class BargainDTO {
    private Long id;
    private String title;
    private String content;
    private Date startDt;
    private Date endDt;

    private BargainFile bargainFile;

    @Builder
    public BargainDTO(Long id, String title, String content, Date startDt, Date endDt, BargainFile bargainFile) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDt = startDt;
        this.endDt = endDt;
        this.bargainFile = bargainFile;
    }
}

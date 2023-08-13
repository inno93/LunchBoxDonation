package com.lunchbox.lunchboxdonation.domain.lunchbox;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
public class LunchBoxDTO {

    private Long id;

    private String lunchboxTitle;
    private String lunchboxThumbNailingIMG;
    private Integer price;
    private LocalDate createdAt;

    @Builder
    public LunchBoxDTO(Long id, String lunchboxTitle, String lunchboxThumbNailingIMG, Integer price, LocalDate createdAt) {
        this.id = id;
        this.lunchboxTitle = lunchboxTitle;
        this.lunchboxThumbNailingIMG = lunchboxThumbNailingIMG;
        this.price = price;
        this.createdAt = createdAt;
    }
}

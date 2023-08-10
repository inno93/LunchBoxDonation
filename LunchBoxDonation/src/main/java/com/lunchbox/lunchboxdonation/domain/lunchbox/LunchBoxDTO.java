package com.lunchbox.lunchboxdonation.domain.lunchbox;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class LunchBoxDTO {

    private Long id;

    private String lunchboxTitle;
    private String lunchboxThumbNailingIMG;
    private Integer price;

    @Builder
    public LunchBoxDTO(Long id, String lunchboxTitle, String lunchboxThumbNailingIMG, Integer price) {
        this.id = id;
        this.lunchboxTitle = lunchboxTitle;
        this.lunchboxThumbNailingIMG = lunchboxThumbNailingIMG;
        this.price = price;
    }
}

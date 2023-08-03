package com.lunchbox.lunchboxdonation.entity.Lunchbox;

import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_LUNCHBOX")
public class Lunchbox extends Timestamp {
    @Id @GeneratedValue
    private Long id;

    private String lunchboxTitle;
    private String lunchboxThumbNailingIMG;
    private Integer price;

//    @OneToMany(fetch = FetchType.LAZY) 단 방향(review DTO에서 넣어 줌)
//    @JoinColumn(name = "REVIEW_ID")
//    private List<Review> review;

    @Builder
    public Lunchbox(String lunchboxTitle, String lunchboxThumbNailingIMG, Integer price) {
        this.lunchboxTitle = lunchboxTitle;
        this.lunchboxThumbNailingIMG = lunchboxThumbNailingIMG;
        this.price = price;
    }
}

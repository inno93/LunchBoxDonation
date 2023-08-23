package com.lunchbox.lunchboxdonation.entity.Lunchbox;

import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = "lunchBoxOptions")
//@Getter @Setter
@NoArgsConstructor
@Table(name = "TBL_LUNCHBOX")
public class LunchBox extends Timestamp {
    @Id @GeneratedValue
    private Long id;

    private String lunchboxTitle;
    private String lunchboxThumbNailingIMG;
    private Integer price;

//    @OneToMany(fetch = FetchType.LAZY) 단 방향(review DTO에서 넣어 줌)
//    @JoinColumn(name = "REVIEW_ID")
//    private List<Review> review;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lunchbox")
    private List<LunchBoxOption> lunchBoxOptions;

    @Builder
    public LunchBox(Long id, String lunchboxTitle, String lunchboxThumbNailingIMG, Integer price, List<LunchBoxOption> lunchBoxOptions) {
        this.id = id;
        this.lunchboxTitle = lunchboxTitle;
        this.lunchboxThumbNailingIMG = lunchboxThumbNailingIMG;
        this.price = price;
        this.lunchBoxOptions = lunchBoxOptions;
    }
}

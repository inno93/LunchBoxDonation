package com.lunchbox.lunchboxdonation.entity.Lunchbox;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="TBL_LUNCHBOXOPTION")
public class LunchBoxOption {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String lunchOptionTitle;
    private Integer lunchOptionPrice;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lunchBox_id", nullable = false)
    private LunchBox lunchbox;

    @Builder
    public LunchBoxOption(String lunchOptionTitle, Integer lunchOptionPrice, LocalDateTime regDate, LocalDateTime modDate, LunchBox lunchbox) {
        this.lunchOptionTitle = lunchOptionTitle;
        this.lunchOptionPrice = lunchOptionPrice;
        this.regDate = regDate;
        this.modDate = modDate;
        this.lunchbox = lunchbox;
    }
}

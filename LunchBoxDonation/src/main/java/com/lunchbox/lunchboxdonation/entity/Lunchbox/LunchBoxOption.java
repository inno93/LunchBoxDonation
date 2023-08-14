package com.lunchbox.lunchboxdonation.entity.Lunchbox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @JoinColumn(name = "LUNCHBOX_ID", nullable = false)
    private LunchBox lunchbox;
}

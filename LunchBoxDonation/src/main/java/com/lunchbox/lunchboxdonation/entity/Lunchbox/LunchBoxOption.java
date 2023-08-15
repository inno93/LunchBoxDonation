package com.lunchbox.lunchboxdonation.entity.Lunchbox;

import com.lunchbox.lunchboxdonation.entity.member.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lunchOptionTitle;
    private Integer lunchOptionPrice;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LUNCHBOX_ID", nullable = false)
    private Lunchbox lunchbox;
}

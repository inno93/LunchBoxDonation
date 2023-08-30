package com.lunchbox.lunchboxdonation.entity.bargain;

import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_BARGAIN")
@NoArgsConstructor
public class Bargain extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BARGAIN_File_ID", nullable = false)
    private BargainFile bargainFile;

    @Builder
    public Bargain(Long id, String title, String content, LocalDateTime startDt, LocalDateTime endDt, BargainFile bargainFile) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDt = startDt;
        this.endDt = endDt;
        this.bargainFile = bargainFile;
    }
}

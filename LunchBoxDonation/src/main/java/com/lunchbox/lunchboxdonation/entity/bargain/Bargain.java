package com.lunchbox.lunchboxdonation.entity.bargain;

import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_BARGAIN")
public class Bargain extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private Date startDt;
    private Date endDt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BARGAIN_File_ID", nullable = false)
    private BargainFile bargainFile;

}

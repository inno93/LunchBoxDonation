package com.lunchbox.lunchboxdonation.entity.monthBargin;

import com.lunchbox.lunchboxdonation.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_BARGIN_FILE")
public class BarginFile {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BARGIN_ID", nullable = false)
    private MonthBargin monthBargin;

    @Column(nullable = false)
    private String fileId;
    private String filePath;
    private String fileName;
    private Date regDate;
    private Date modDate;



}

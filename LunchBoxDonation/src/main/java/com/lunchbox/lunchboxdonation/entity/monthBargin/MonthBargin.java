package com.lunchbox.lunchboxdonation.entity.monthBargin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_MONTH_BARGIN")
public class MonthBargin {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private Date startDt;
    private Date endDt;
    private Date regDate;
    private Date modDate;

}

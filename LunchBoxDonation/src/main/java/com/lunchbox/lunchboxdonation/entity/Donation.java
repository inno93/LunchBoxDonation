package com.lunchbox.lunchboxdonation.entity;

import com.lunchbox.lunchboxdonation.entity.Lunchbox.Lunchbox;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_DONATION")
public class Donation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LUNCHBOX_ID")
    private Lunchbox lunchbox;

    @Column(nullable = false)
    private Integer count;
    private Integer price;
    private Date donationDate;
    private Date regDate;
    private Date modDate;



}

package com.lunchbox.lunchboxdonation.entity.Order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "TBL_ORDER_ITEMS")

public class OrderAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(nullable = false)
    private String orderName;
    private String orderPhone;
    private String addr;
    private String addrDtl;
    private Integer postNum;
    private LocalDateTime modDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime regDate;

    @Builder
    private OrderAddress(String orderName, String orderPhone, String addr, String addrDtl, Integer postNum){
        this.orderName = orderName;
        this.orderPhone = orderPhone;
        this.addr = addr;
        this.addrDtl = addrDtl;
        this.postNum = postNum;
    }

//
//    public void setModDate(LocalDateTime modDate){this.modDate = modDate;}
//
//    public void setRegDate(LocalDateTime regDate){this.regDate = regDate;}



}

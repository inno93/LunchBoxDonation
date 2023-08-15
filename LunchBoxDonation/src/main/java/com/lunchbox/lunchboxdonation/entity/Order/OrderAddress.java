package com.lunchbox.lunchboxdonation.entity.Order;

import com.lunchbox.lunchboxdonation.domain.OrderAddressDTO;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
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

public class OrderAddress extends Timestamp {

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
    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime regDates;

    @Builder
    private OrderAddress(OrderAddressDTO orderAddressDTO){
        this.orderName = orderName;
        this.orderPhone = orderPhone;
        this.addr = addr;
        this.addrDtl = addrDtl;
        this.postNum = postNum;
        getCreatedAt();
        getUpdateTime();
    }

    public static OrderAddress of(OrderAddressDTO orderAddressDTO){
        return OrderAddress.builder()
                .orderAddressDTO(orderAddressDTO)
                .build();
    }
//
//    public void setModDate(LocalDateTime modDate){this.modDate = modDate;}
//
//    public void setRegDate(LocalDateTime regDate){this.regDate = regDate;}



}

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
    @GeneratedValue
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
        this.orderName = orderAddressDTO.getOrderName();
        this.orderPhone = orderAddressDTO.getOrderPhone();
        this.addr = orderAddressDTO.getAddr();
        this.addrDtl = orderAddressDTO.getAddrDtl();
        this.postNum = orderAddressDTO.getPostNum();
    }

    public static OrderAddress of(OrderAddressDTO orderAddressDTO){
        return OrderAddress.builder()
                .orderAddressDTO(orderAddressDTO)
                .build();
    }

}

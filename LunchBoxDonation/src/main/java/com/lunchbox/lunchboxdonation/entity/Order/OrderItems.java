package com.lunchbox.lunchboxdonation.entity.Order;

import com.lunchbox.lunchboxdonation.domain.Order.OrderDTO;
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

public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(nullable = false)
    private String order_name;
    private String order_phone;
    private String addr;
    private String addr_dtl;
    private int post_num;
    private LocalDateTime mod_date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime reg_date;

    @Builder
    private OrderItems(String order_name, String order_phone, String addr,String addr_dtl, int post_num){
        this.order_name = order_name;
        this.order_phone = order_phone;
        this.addr = addr;
        this.addr_dtl = addr_dtl;
        this.post_num = post_num;
    }

    public void setModDate(LocalDateTime mod_date){this.mod_date = mod_date;}

    public void setRegDate(LocalDateTime reg_date){this.reg_date = reg_date;}



}

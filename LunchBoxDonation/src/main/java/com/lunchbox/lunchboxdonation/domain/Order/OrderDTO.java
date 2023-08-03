package com.lunchbox.lunchboxdonation.domain.Order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderDTO {

    private int Order_price;
    private int Order_quantity;
    private LocalDateTime Order_date;

}

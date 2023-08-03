package com.lunchbox.lunchboxdonation.domain.Order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderDTO {

    private int OrderPrice;
    private int OrderQuantity;
    private LocalDateTime OrderDate;

}

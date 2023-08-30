package com.lunchbox.lunchboxdonation.service.order;

import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.entity.Review.Review;

public interface OrderAddressService {
    public void save(OrderAddress orderAddress);
}

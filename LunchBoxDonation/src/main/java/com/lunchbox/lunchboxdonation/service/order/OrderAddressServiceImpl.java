package com.lunchbox.lunchboxdonation.service.order;

import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.repository.orderaddress.OrderAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderAddressServiceImpl implements OrderAddressService{

    @Autowired
    private OrderAddressRepository orderAddressRepository;


    @Override
    public void save(OrderAddress orderAddress) {
         orderAddressRepository.save(orderAddress);
    }
}

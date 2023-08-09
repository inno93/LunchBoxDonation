package com.lunchbox.lunchboxdonation.domain;

import lombok.Getter;

@Getter
public class OrderAddressDTO {
    private String orderName;
    private String orderPhone;
    private String addr;
    private String addrDtl;
    private Integer postNum;
}

package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

import java.math.BigDecimal;

public interface DiscountInterface {
    Integer getDiscountedAmount(PayRequest payRequest);
}

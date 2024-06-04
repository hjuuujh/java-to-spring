package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public class DiscountByPayMethod implements DiscountInterface{
    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        switch (payRequest.getPayMethodType()){
            case CARD:
                return payRequest.getPayAmount() * 7/10;
            case MONEY:
                return payRequest.getPayAmount();
        }
        return payRequest.getPayAmount();
    }
}

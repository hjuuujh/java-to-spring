package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.type.PayResult;

import java.math.BigDecimal;

public class DiscountByConvenience implements DiscountInterface{
    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {

        switch(payRequest.getConvenienceType()){
            case G25:
                return payRequest.getPayAmount() * 8/10;
            case GU:
                return payRequest.getPayAmount() * 9/10;
            case Seven:
                return payRequest.getPayAmount();
        }
        return payRequest.getPayAmount();
    }
}

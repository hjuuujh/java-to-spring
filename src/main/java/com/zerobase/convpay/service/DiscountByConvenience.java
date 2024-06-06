package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.type.PayResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
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

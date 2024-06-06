package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.type.PayResult;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("test") // 해당 환경에서만 동작하는 Bean을 만들기위해
public class DiscountByConvenience implements DiscountInterface{
    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        System.out.println("called DiscountByConvenience.getDiscountedAmount");
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

package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary // 빈의 구현체가 여러개인 경우 주입받는 방법 1
@Profile("production") // 해당 환경에서만 동작하는 Bean을 만들기위해

public class DiscountByPayMethod implements DiscountInterface{
    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        System.out.println("called DiscountByPayMethod.getDiscountedAmount");
        switch (payRequest.getPayMethodType()){
            case CARD:
                return payRequest.getPayAmount() * 7/10;
            case MONEY:
                return payRequest.getPayAmount();
        }
        return payRequest.getPayAmount();
    }
}

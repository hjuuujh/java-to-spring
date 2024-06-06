package com.zerobase.convpay.service;

import com.zerobase.convpay.type.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
// bean의 스코프
@Scope("singleton") // 일반적 방법 하나만 만들어서 계속 재활용
// prototype 매번 새로 만드는 방법
//@Scope("request") 요청따라 계속 새로 만듦
//@Scope("session") 세션마다 계속 새로 만듦
//@Scope("prototype")
public class CardAdapter implements PaymentInterface {
    // 1. 인증
    public void authorization() {
        System.out.println("authorization success");
    }

    // 2. 승인
    public void approval() {
        System.out.println("approval success");

    }

    // 3. 매입
    public CardUseResult capture(Integer payAmount) {
        if (payAmount > 100) {
            return CardUseResult.USE_FAIL;
        }

        return CardUseResult.USE_SUCCESS;
    }

    // 4. 매입 취소
    public CardUseCancelResult cancelCapture(Integer cancelAmount) {
        if (cancelAmount < 1000) {
            return CardUseCancelResult.USE_CANCEL_FAIL;
        }

        return CardUseCancelResult.USE_CANCEL_SUCCESS;
    }

    @Override
    public PayMethodType getPayMethodType() {
        return PayMethodType.CARD;
    }

    @Override
    public PaymentResult payment(Integer payAmount) {
        authorization();
        approval();
        CardUseResult cardUseResult = capture(payAmount);
        if (cardUseResult == CardUseResult.USE_FAIL) {
            return PaymentResult.PAYMENT_FAIL;
        }
        return PaymentResult.PAYMENT_SUCCESS;

    }

    @Override
    public CancelPaymentResult cancelPayment(Integer cancelAmount) {
        CardUseCancelResult cardUseCancelResult = cancelCapture(cancelAmount);

        if (cardUseCancelResult == CardUseCancelResult.USE_CANCEL_FAIL) {
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }
        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}

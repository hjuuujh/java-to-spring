package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ConveniencePayService {

//    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
//    private final CardAdapter cardAdapter = new CardAdapter();
//    private final DiscountInterface discountInterface = new DiscountByConvenience();

    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet, DiscountInterface discountByConvenience) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );
        this.discountInterface = discountByConvenience;
    }

    // 결제
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

//        if(payRequest.getPayMethodType() == PayMethodType.CARD) {
//            paymentInterface = cardAdapter;
//        }else {
//            paymentInterface = moneyAdapter;
//        }

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult payment = paymentInterface.payment(discountedAmount);

        // fail test
        // 여러개의 exception case
        // 하나의 success case
        // -> if else 사용x
        // => 예외 케이스끼리 하고 성공케이스를 가장 마지막에

        if(payment == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        // Success Case 성공 케이스인경우 끝까지 내려오도록
        return new PayResponse(PayResult.SUCCESS, discountedAmount);

    }

    // 결제 취소
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

//        if(payCancelRequest.getPayMethodType() == PayMethodType.CARD) {
//            paymentInterface = cardAdapter;
//        }else {
//            paymentInterface = moneyAdapter;
//        }

        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());
        if(cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL){
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }

        return  new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
    }
}
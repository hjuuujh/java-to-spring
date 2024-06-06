package com.zerobase.convpay;

import com.zerobase.convpay.config.ApplicationConfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserClient {
    public static void main(String[] args) {
        // spring이 하는 일 직접 작성
        // [사용자] -> 편결이 -> 머니
//        ApplicationConfig aConfig = new ApplicationConfig();
//        ConveniencePayService conveniencePayService = aConfig.conveniencePayServiceDiscountPayMethod();
        // application context가 spring
        // ApplicationConfig 파일 사용해 설정 : bean들 등록되어있음
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // xml 파일 이용해 등록
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ConveniencePayService conveniencePayService = context.getBean("conveniencePayService", ConveniencePayService.class);

        // G25 결제 1000원
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 50);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println(payResponse);

        // G25 취소 500원
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        System.out.println(payCancelResponse);
    }
}

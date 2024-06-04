package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;

public class ApplicationConfig {

//    public ConveniencePayService conveniencePayServiceDiscountConvenience(){
//        return new ConveniencePayService(
//                new HashSet<>(
//                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
//                ),
//                new DiscountByConvenience()
//        );
//    }
//
//    public ConveniencePayService conveniencePayServiceDiscountPayMethod(){
//        return new ConveniencePayService(
//                new HashSet<>(
//                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
//                ),
//                new DiscountByPayMethod()
//        );
//    }

    // ConveniencePayService 에서 생성된 것들을 담을 컨테이너를 생성하는게 Bean
    @Bean
    public ConveniencePayService conveniencePayService() {
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(moneyAdapter(), cardAdapter())
                ),
                getDiscountByConvenience()
        );
    }

    @Bean
    public CardAdapter cardAdapter() {
        return new CardAdapter();
    }

    @Bean
    public MoneyAdapter moneyAdapter() {
        return new MoneyAdapter();
    }

    @Bean
    public  DiscountByConvenience getDiscountByConvenience() {
        return new DiscountByConvenience();
    }
}

package springstudy.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import springstudy.core.discount.DiscountPolicy;
import springstudy.core.discount.FixDiscountPolicy;
import springstudy.core.discount.RateDiscountPolicy;
import springstudy.core.member.MemberRepository;
import springstudy.core.member.MemberService;
import springstudy.core.member.MemberServiceImpl;
import springstudy.core.member.MemoryMemberRepository;
import springstudy.core.order.OrderService;
import springstudy.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}

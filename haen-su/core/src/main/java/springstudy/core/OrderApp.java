package springstudy.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springstudy.core.member.*;
import springstudy.core.order.Order;
import springstudy.core.order.OrderService;
import springstudy.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // OrderService orderService = appConfig.orderService();
        // MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}

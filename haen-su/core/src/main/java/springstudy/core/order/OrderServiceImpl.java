package springstudy.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springstudy.core.discount.DiscountPolicy;
import springstudy.core.discount.FixDiscountPolicy;
import springstudy.core.discount.RateDiscountPolicy;
import springstudy.core.member.Member;
import springstudy.core.member.MemberRepository;
import springstudy.core.member.MemberServiceImpl;
import springstudy.core.member.MemoryMemberRepository;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

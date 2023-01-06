package springstudy.core.discount;

import springstudy.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}

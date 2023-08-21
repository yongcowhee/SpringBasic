package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberReprository;

public class OrderServiceImpl implements OrderService {
    // 철저하게 interface에만 의존함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    // FixDiscountPolicy가 들어올지 RateDiscountPolicy가 들어올지 모르는 상태로 discountPolicy에 관한 로직만 실행하면 됨,
    // 철저히 DIP를 지키고 있음
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인에 대해서는 discountPolicy가 알아서 해달라고 던져준 것으로 설계해서 단일책임 원칙을 잘 지킨 것

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

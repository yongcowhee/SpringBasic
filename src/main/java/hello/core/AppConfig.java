package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberReprository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션 전체를 설정하고 구성하여 전반적인 운영을 책임짐
// 애플리케이션의 실제 동작에 필요한 구현 객체를 생성하는 역할
@Configuration
public class AppConfig {
    // @Bean 어노테이션을 붙이면 spring 컨테이너에 올라간다
    // Bean 이름은 항상 다른 이름을 부여해야 함. 같은 이름을 부여하면 다르 빈이 무시되거나 기존 빈을 덮어버리거나 설정에 따라 오류 발생
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberReprository memberRepository() {
        return new MemoryMemberReprository();
    }
    // Dependency Injection -> 의존관계 주입/의존성 주입

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        // 할인 정책이 변경돼도 이 부분만 고치면 된다. 분리가 확실하게 이뤄졌기 때문.
    }
}

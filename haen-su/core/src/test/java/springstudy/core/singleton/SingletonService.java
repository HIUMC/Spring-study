package springstudy.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springstudy.core.AppConfig;
import springstudy.core.member.MemberService;

import java.security.Signature;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 막음
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println(singletonService1);
        System.out.println(singletonService2);

        Assertions.assertThat(singletonService1).isEqualTo(singletonService2);
        singletonService1.logic();

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}

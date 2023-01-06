package springstudy.core.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springstudy.core.AppConfig;
import springstudy.core.member.Member;
import springstudy.core.member.MemberService;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

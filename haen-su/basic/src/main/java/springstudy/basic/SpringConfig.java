package springstudy.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstudy.basic.repository.JpaMemberRepository;
import springstudy.basic.repository.MemberRepository;
import springstudy.basic.repository.MemoryMemberRepository;
import springstudy.basic.repository.SpringDataJpaMemberRepository;
import springstudy.basic.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}

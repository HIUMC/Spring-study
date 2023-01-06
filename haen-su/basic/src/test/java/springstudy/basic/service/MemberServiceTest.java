package springstudy.basic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springstudy.basic.domain.Member;
import springstudy.basic.repository.MemberRepository;
import springstudy.basic.repository.MemoryMemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("member1");

        //when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member1");

        //when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void 전체_회원_조회() {
        // given
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member2");
        memberService.join(member1);
        memberService.join(member2);

        //when
        List<Member> memberList = memberService.findMembers();

        // then
        assertThat(memberList.size()).isEqualTo(2);
    }

    @Test
    public void 회원_한명_조회() {
        // given
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member2");
        memberService.join(member1);
        memberService.join(member2);

        //when
        Member findMember = memberService.findOne(member1.getId()).get();

        // then
        assertThat(findMember).isEqualTo(member1);
    }
}
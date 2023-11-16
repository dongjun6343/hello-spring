package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    // 클리어를 해야한다.
    // new MemoryMemberRepository(); 하는건 좀 애매하다... -> service 변경.
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() {
        // 동작하기전에 넣는다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    @DisplayName("회원가입")
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findeMember = memberService.findOne(saveId).get();
        Assertions.assertThat(findeMember.getName()).isEqualTo(findeMember.getName());
    }

    @Test
    @DisplayName("증복 회원 예외")
    void join2(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // () -> memberService.join(member2) 이 로직을 실행을 할건데 IllegalStateException.class 이게 발생해야돼!
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
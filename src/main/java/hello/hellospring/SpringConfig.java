package hello.hellospring;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 자바 코드로 직접 스프링 빈 등록하기.
    // DI에는 필드 주입 , setter 주입, 생성자 주입이 있다. -> 의존관계가 실행중에 동적으로 변하는 경우는 없으므로 생성자 주입을 권장한다.

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    // aop
    // @Bean으로 생성하거나, TimeTraceAop에 @Component 등록하거나 한다.
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}

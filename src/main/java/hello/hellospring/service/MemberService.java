package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // -> new로 생성하는 것 대신에 외부에서 넣어줌. (DI - dependency Injection)
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // 값이 있으면 exception. -> optional
        //result.ifPresent(m -> {
        //    throw new IllegalStateException();
        //});


        // option이 있으면 좀 지저분해! -> 바로 ifPresent사용할 수 있다. -> 그리고 메서드로 분리(cmd+option+m)
        validationDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validationDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /**
     * 전체 회원 조회
     * 서비스는 비즈니스에 의존적으로 설계.(용어선택 시)
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

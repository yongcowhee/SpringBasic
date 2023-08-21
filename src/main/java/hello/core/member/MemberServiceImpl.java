package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //DIP를 지키며 추상화에만 의존하게 함 -> 생성자 주입
    private final MemoryMemberReprository memberRepository;

    public MemberServiceImpl(MemoryMemberReprository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원조회
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

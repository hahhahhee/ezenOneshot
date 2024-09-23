package ezen.oneshot.service;

import ezen.oneshot.domain.dao.Member;
import ezen.oneshot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Long join(Member member) {

        // 같은 이름이 있는 중복회원 검증
        validateDuplicateMember(member);
        this.memberRepository.save(member);
        return member.getId();
    }

    // 중복회원조회메서드
    private void validateDuplicateMember(Member member) {
        Optional<Member> findMemeber = memberRepository.findByLoginId(member.getLoginId());
        if (findMemeber.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 전체 회원 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public  Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 회원 삭제
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    // 회원 수정


}

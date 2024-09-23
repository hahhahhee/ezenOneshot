package ezen.oneshot.service;

import ezen.oneshot.domain.dao.Member;
import ezen.oneshot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        if (findMemberOptional.isPresent()) {
            Member member = findMemberOptional.get();
            if (member.getPassword().equals(password)) {
                return member;
            }
            else {
                return null;
            }
        }
        return null;
    }
}

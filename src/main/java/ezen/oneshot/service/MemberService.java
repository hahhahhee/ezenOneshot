package ezen.oneshot.service;

import ezen.oneshot.domain.dao.Answer;
import ezen.oneshot.domain.dao.Membership;
import ezen.oneshot.domain.dao.Question;
import ezen.oneshot.repository.AnswerRepository;
import ezen.oneshot.repository.MemberRepository;
import ezen.oneshot.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;

    // 회원가입
    public Long join(Membership membership) {

        // 같은 이름이 있는 중복회원 검증
        validateDuplicateMember(membership);
        this.memberRepository.save(membership);
        return membership.getId();
    }

    // 중복회원조회메서드
    private void validateDuplicateMember(Membership membership) {
        Optional<Membership> findMemeber = memberRepository.findByLoginId(membership.getLoginId());
        if (findMemeber.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 전체 회원 조회
    public List<Membership> findAll() {
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public  Optional<Membership> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 회원 삭제
    public void delete(Long memberId) {
        // 회원이 작성한 질문 삭제
        List<Question> questions = questionRepository.findByAuthorId(memberId, Sort.by(Sort.Direction.DESC, "createDate"));
        for (Question question : questions) {
            questionService.delete(question);
        }

        // 회원이 작성한 답변 삭제
        List<Answer> answers = answerRepository.findByAuthorId(memberId, Sort.by(Sort.Direction.DESC, "createDate"));
        for (Answer answer : answers) {
            answerService.delete(answer);
        }

        // 마지막으로 회원 삭제
        memberRepository.deleteById(memberId);
    }

    // 회원 수정
    public Long update(Membership membership) {
        // 회원 저장
        memberRepository.save(membership);
        return membership.getId();
    }

}

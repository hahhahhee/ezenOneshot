package ezen.oneshot.controller;

import ezen.oneshot.domain.dao.Member;
import ezen.oneshot.domain.dto.MemberForm;
import ezen.oneshot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addMemeber(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "user/addMemberForm";
    }

    @PostMapping("/add")
    public String create(@Validated @ModelAttribute("memberForm") MemberForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/addMemberForm";
        }

        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setBirthdate(form.getBirthdate());
        member.setGender(form.getGender());
        member.setEmail(form.getEmail());
        member.setEmailOptIn(form.isEmailOptIn());

        memberService.join(member);
        return "redirect:/";
    }

    // 멤버 수정폼 보여주기 - 마이페이지


    // 멤버 수정하고 저장하기



    // 회원 목록 보기 - 관리자 Page (Admin)
    @GetMapping("/members")
    public String list(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        model.addAttribute("loginMember", loginMember);
        return "admin/memberList";
    }

    // 회원 삭제 하기 - 관리자 Page (Admin)
    @GetMapping("/members/{memberid}/delete")
    public String delete(@PathVariable(value = "memberid") Long memberid) {
        Optional<Member> findMember = memberService.findOne(memberid);

        if (findMember.isPresent()) {
            memberService.delete(findMember.get());
        }
        return "redirect:/members";
    }
}

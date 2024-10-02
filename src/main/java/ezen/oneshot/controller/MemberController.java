package ezen.oneshot.controller;

import ezen.oneshot.domain.dao.Membership;
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

        Membership membership = new Membership();
        membership.setLoginId(form.getLoginId());
        membership.setPassword(form.getPassword());
        membership.setName(form.getName());
        membership.setBirthdate(form.getBirthdate());
        membership.setGender(form.getGender());
        membership.setEmail(form.getEmail());
        membership.setEmailOptIn(form.isEmailOptIn());

        memberService.join(membership);
        return "redirect:/";
    }

    // 회원 목록 보기 - 관리자 Page
    @GetMapping("/members")
    public String list(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Membership loginMember, Model model) {
        List<Membership> members = memberService.findAll();
        model.addAttribute("members", members);
        model.addAttribute("loginMember", loginMember);
        return "admin/memberList";
    }

    // 회원 삭제 하기 - 관리자 Page
    @PostMapping("/members/{memberid}/delete")
    public String delete(@PathVariable(value = "memberid") Long memberid) {
        Optional<Membership> findMember = memberService.findOne(memberid);

        if (findMember.isPresent()) {
            memberService.delete(memberid);
        }
        return "redirect:/members";
    }

}

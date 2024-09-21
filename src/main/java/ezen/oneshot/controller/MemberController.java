package ezen.oneshot.controller;

import ezen.oneshot.domain.dto.MemberForm;
import ezen.oneshot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addMemeber(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "user/addMemberForm";
    }

    @GetMapping("/members")
    public String list() {
        return "admin/memberList";
    }
}

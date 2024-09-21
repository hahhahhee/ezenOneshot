package ezen.oneshot.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MemberForm {

    private Long id;

    @NotEmpty(message = "아이디 입력은 필수입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    private String password;

    @NotEmpty(message = "이름 입력은 필수입니다.")
    private String name;

    @NotEmpty(message = "생년월일 입력은 필수입니다.")
    private LocalDate birthdate;

    private String gender;

    private String email;

    private String emailOptIn;
}

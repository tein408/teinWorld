package kr.teinworld.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

    @NotBlank(message = "회원 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    private String pwd;

    private String auth;

}

package kr.teinworld.controller;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberForm {

    @NotBlank(message = "회원 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    private String pwd;

    private String auth;

}

package kr.teinworld.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String pwd;

    private String auth;

    public MemberForm() {
    }

    public MemberForm(String name, String email, String pwd, String auth) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.auth = auth;
    }

}

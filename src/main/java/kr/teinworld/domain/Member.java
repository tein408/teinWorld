package kr.teinworld.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    @Column(length = 15)
    private String name;

    private String pwd;

    private String auth;



}

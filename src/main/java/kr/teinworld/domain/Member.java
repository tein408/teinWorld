package kr.teinworld.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String email;
    private String name;
    private String pwd;



}

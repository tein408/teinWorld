package kr.teinworld.controller;

import kr.teinworld.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardForm {

    private String title;
    private String content;
    private Member member;
    private int hit;
    private int like;
    private LocalDateTime regDate;

}

package kr.teinworld.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime regDate;

    private int hit;

    @Column(name = "likes")
    private int like;

    public Board(String title, String content, Member member, LocalDateTime regDate, int hit, int like) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.regDate = regDate;
        this.hit = hit;
        this.like = like;
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void changeHit(int hit) {
        this.hit = hit;
    }

}

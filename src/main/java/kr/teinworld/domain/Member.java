package kr.teinworld.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {
//UserDetails : 사용자의 정보를 담는 interface
//Spring Security에서 구현한 클래스를 사용자 정보로 인식하고 인증 작업하기 위해 필요

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    @Column(length = 15)
    private String name;

    private String pwd;

    @Column(length = 15)
    private String auth;

    public Member(String email, String name, String pwd, String auth) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.auth = "ROLE_"+auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(this.auth));
        return authList;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //============//
    // 계정 만료/잠금, 비밀번호 만료, 계정 사용 여부 - 아직 세부적인 기능을 구현하지 않아 true return
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //============//

}

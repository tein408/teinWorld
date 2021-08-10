package kr.teinworld.config;

import kr.teinworld.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "home").permitAll()
                .antMatchers("/members/new", "/members/signUp", "/members/dupEmail").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/members/signUp")
                .usernameParameter("email")
                .passwordParameter("pwd")
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .logoutUrl("/members/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        ;
    }

    //AuthenticationManagerBuilder : AuthenticationManager 생성하는 class
    // * AuthenticationManager : Spring Security의 모든 인증 관리 interface
    //AuthenticationManagerBuilder가 UserDetailService를 통해 유저 정보를 memberService에서 찾아 담음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}

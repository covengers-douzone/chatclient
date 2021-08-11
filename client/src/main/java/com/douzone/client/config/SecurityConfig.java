package com.douzone.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // 해당 메서드의 return 되는 오브젝트를 IOC로 등록해준다.(어디서든 사용가능)
    public BCryptPasswordEncoder encoderPwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                //.antMatchers() -> 권한 부여 접속.
                .anyRequest().permitAll() // 설정외의 모든 페이지는 접속이 허용가능.
                .and()
                .formLogin()
                .loginPage("/")

                //로그인 부분(controller에서 따로 만들어주지 않아도 됨.)
                .loginProcessingUrl("/login") // login주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .defaultSuccessUrl("/main/success"); // 로그인 성공시 /~ 로 화면을 보여줌.

    }
}

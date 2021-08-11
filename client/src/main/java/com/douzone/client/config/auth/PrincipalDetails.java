package com.douzone.client.config.auth;

import com.douzone.client.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*
    1. 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴.
    2. 로그인 진행이 완료가 되면 시큐리티 session을 만들어준다.
    3. 시큐리티가 가지고 잇는 세션 영역이 있는데,
    4. 여기에 세션 정보를 저장해주는데
    5. 여기에 들어갈 수 있는 객체가 Authentication 객체여야 하고
    6. Authentication 객체안의 User정보를 저장할 때
    7. UserDetails type이어야 한다.
*/

// implement를 해주면 UserDetails, PrincipalDetails는 type이 같으므로 PrincipalDetails이  객체를 Authentication 객체안에 넣을 수 있다.

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }


    // 해당 user의 권한을 리턴하는곳.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }


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
        // 1년동안 사이트 로그인을 하지 않았다면 휴먼계정으로 전환.
        // 현재시간 - 로그인 시간 > 1년 초과시 return false로 전환.
        return true;
    }
}

package com.douzone.client.config.auth;

import com.douzone.client.model.User;
import com.douzone.client.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 되어있는 loadUserByUsername 함수가 실행.
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session = Authentication = UserDetails
    @Override                                   //↓↓ 로그인의 name 과 같아야한다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // user가 있는지 확인.
        User user = userRepository.findByUsername(username);

        if(user == null){
            return null;
        }
        return new PrincipalDetails(user);
    }
}

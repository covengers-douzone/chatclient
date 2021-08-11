package com.douzone.client.repository;

import com.douzone.client.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 기본적인 CRUD를 JpaRepository를 가지고 있음
// @Repository 어노테이션이 없어도 JpaRepository 를 상속했기 때문에 IOC가 가능하다.
// 필요한곳에서 Autowired를 해주면 된다.
public interface UserRepository extends JpaRepository<User, Integer>  {

    User findByUsername(String username);



}

package com.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Integer> {

}

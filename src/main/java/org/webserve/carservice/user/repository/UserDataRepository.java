package org.webserve.carservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webserve.carservice.security.User;

public interface UserDataRepository extends JpaRepository<User, Long> {

}

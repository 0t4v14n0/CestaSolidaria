package com.CestaSolidaria.domain.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.CestaSolidaria.domain.user.enums.Status;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByCpf(String subject);

	List<User> findByStatus(Status status, Pageable pageable);

}

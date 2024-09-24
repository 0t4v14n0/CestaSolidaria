package com.CestaSolidaria.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.CestaSolidaria.domain.user.enums.Status;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByCpf(String cpf);

	Page<User> findByStatus(Status status, Pageable pageable);

}

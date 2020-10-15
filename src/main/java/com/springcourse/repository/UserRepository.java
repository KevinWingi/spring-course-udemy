package com.springcourse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query("SELECT u FROM user u WHERE email = ?1 AND password = ?2")
	public Optional<User> login(String email, String password);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE user SET role = ?2 WHERE id = ?1")
	public int updateRole(Long id, Role role);
	
	public Optional<User> findByEmail(String email);
	
}

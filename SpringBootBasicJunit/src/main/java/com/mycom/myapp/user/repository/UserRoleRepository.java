package com.mycom.myapp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.user.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	//crud 자동 구현
	
	// 이름으로 검색, 영속화
	UserRole findByName(String name);
}

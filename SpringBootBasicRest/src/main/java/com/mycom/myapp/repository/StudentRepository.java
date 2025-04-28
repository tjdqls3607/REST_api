package com.mycom.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.entity.Student;

// repository 역할을 담당하는 구현 코드를 Spring Data Jpa 가 대신 생성, 수행
// extends JpaRepository 코드에 의해 대응되는 엔티티에 대한 기본 crud 및 기타 몇 가지 메소드가 자동 생성
public interface StudentRepository extends JpaRepository<Student, Integer>{
	// 간단한 crud 및 카운트, 페이징 등은 아무런 메소드 선언조차 필요 없다
	
	// find
	// sql : 다양한 select
	// 표현을 잘하면 자동으로 jpql 만들어줌
	List<Student> findByName(String name);
	List<Student> findByEmailAndPhone(String email, String phone);
	List<Student> findByEmailOrPhone(String email, String phone);
	List<Student> findByNameLike(String name);
}

package com.mycom.myapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// insert 는 생성자 이슈 X
// update 는 기본생성자, 전체생성자가 필요
// update 의 save 는 select 한 후, update 수행
// select 한 결과를 Entity 로 setting 할 때 필요
@Data
@Entity
@Table(name="student")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String phone;
}

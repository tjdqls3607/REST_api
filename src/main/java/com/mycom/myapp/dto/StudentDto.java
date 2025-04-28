package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Controller 의 파라미터로 사용되는 Dto 는 기본생성자 + 전체 생성자를 포함해야 한다
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	private int id;
	private String name;
	private String email;
	private String phone;
	
//	public StudentDto() {}
//	public StudentDto(Integer id, String name, String email, String phone) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.phone = phone;
//	}
//	
	
}

package com.mycom.myapp.user.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	
	private Map<Integer, String> roles;
}

package com.mycom.myapp.user.dto;

import java.util.List;

import lombok.Data;

@Data
// @Builder 없음.
public class UserResultDto {
	private String result;
	private UserDto userDto;
	private List<UserDto> userList;
}

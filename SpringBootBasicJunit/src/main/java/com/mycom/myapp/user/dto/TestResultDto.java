package com.mycom.myapp.user.dto;

import lombok.Data;

@Data
public class TestResultDto {
    private String result;
    private long count;
    private TestUserDto testUserDto;
}

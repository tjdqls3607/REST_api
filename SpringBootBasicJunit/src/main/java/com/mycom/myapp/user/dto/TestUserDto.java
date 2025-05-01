package com.mycom.myapp.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestUserDto {
    private long id;
    private String name;
    private String email;
    private String password;

    private List<String> phone;
}

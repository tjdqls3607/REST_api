package com.mycom.myapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class StudentResultDto {
	private String result;
	private StudentDto studentDto;
	private List<StudentDto> studentList;
	private Long count;
}
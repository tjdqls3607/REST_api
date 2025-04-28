package com.mycom.myapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.entity.Student;
import com.mycom.myapp.service.StudentServiceCrud;

import lombok.RequiredArgsConstructor;

// REST 를 적용하면 /api/v1
// get list : / students 
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentControllerCrud {
	
	private final StudentServiceCrud studentServiceCrud; 
	
	@GetMapping("/students")	//목록
	 public StudentResultDto listStudent() {
		return studentServiceCrud.listStudent();
	}
	
	@GetMapping("/students/{id}")	//상세
	 public StudentResultDto detailStudent(@PathVariable("id") Integer id) {
		return studentServiceCrud.detailStudent(id);
	}
	
	@PostMapping("/students")
	public StudentResultDto insertStudent(StudentDto studentDto) {
		return studentServiceCrud.insertStudent(studentDto);
	}
	
	@PutMapping("/students/{id}")
	public StudentResultDto updateStudent(@PathVariable("id") Integer id, StudentDto studentDto) {
		 studentDto.setId(id);
		return studentServiceCrud.updateStudent(studentDto);
	}
	
	@DeleteMapping("/students/{id}")	//삭제
	 public StudentResultDto deleteStudent(@PathVariable("id") Integer id) {
		return studentServiceCrud.deleteStudent(id);
	}
	
	@GetMapping("/students/count")
	public StudentResultDto countStudent() {
		return studentServiceCrud.countStudent();
	}
	
	@GetMapping("/students/page")
	public StudentResultDto listStudent(
			@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("pageSize") Integer pageSize){
		
		return studentServiceCrud.listStudent(pageNumber, pageSize);
	}
}

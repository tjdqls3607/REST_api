package com.mycom.myapp.controller;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ResponseEntity 만 사용
//	1. ResultDto 사용 X, 대신 예외 처리로 오류 파악, 이를 통해서 ResponseEntity 의 응답코드를 결정
// ResponseEntity + ResultDto 함께 사용
// 	1. ResultDto 을 client 에게 전달, Client 가 Server 의 작업 결과를 ResultDto 를 통해서 처리
//	2. ResultDto를 Client 에게 전달 X, 대신 Controller 에서 Server 에서 return 한 ResultDto 객체를 이용해서
//		ResponseEntity 의 응답코드를 결정
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json/re")
public class StudentControllerCrudJsonRequestRE {
	private final StudentServiceCrud studentServiceCrud;
	
	@GetMapping("/students") // Get: list
	public ResponseEntity<StudentResultDto> listStudent(){
		StudentResultDto  studentResultDto = studentServiceCrud.listStudent();
		// ResponseEntity + ResultDto 함께 사용 의 #1
//		return new ResponseEntity<StudentResultDto>(studentResultDto, HttpStatus.OK);

		// 500 에러로 status 코드를 보내도, body 에 데이터가 있으면 브라우저에서 예외 처리 X
//		return new ResponseEntity<StudentResultDto>(studentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);

		// 500 에러로 status 코드를 보내도, body 에 데이터 X
//		return new ResponseEntity<StudentResultDto>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		// 200 OK로 status 코드를 보내도 body 에 데이터 X -> try-catch 에서 예외 발생
//		return new ResponseEntity<>(null, HttpStatus.OK);

		// ResponseEntity 객체를 생성, 리턴하는 다른 표현
//		return ResponseEntity
//				.status(HttpStatus.OK)
//				.body(studentResultDto);

//		return ResponseEntity
//				.ok()
//				.body(studentResultDto);

		return ResponseEntity
				.notFound()
				.build();	//body() 오류 <- body를 채우면 client 오류 처리 X
	}

	
	@GetMapping("/students/{id}")
	public StudentResultDto detailStudent(@PathVariable("id") Integer id) {
	    return studentServiceCrud.detailStudent(id);
	}

	// 등록, 수정에 사용되는 StudentDto를 Cㅣient 에서 JSON으로 보낸다.
	@PostMapping("/students") // Post: insert
	public StudentResultDto insertStudent(@RequestBody StudentDto studentDto) {
	    return studentServiceCrud.insertStudent(studentDto);
	}
	
	@PutMapping("/students/{id}") // Put: update
	public StudentResultDto  updateStudent(@PathVariable("id") Integer id, StudentDto studentDto) {
	    studentDto.setId(id);
		return studentServiceCrud.updateStudent(studentDto);
	}
	
	@DeleteMapping("/students/{id}")
	public StudentResultDto deleteStudent(@PathVariable("id") Integer id) {
	    return studentServiceCrud.deleteStudent(id);
	}

	@GetMapping("/students/count")
	public StudentResultDto countStudent() {
		return studentServiceCrud.countStudent();
	}
	
	@GetMapping("/students/page")
	public StudentResultDto listStudent(@RequestParam("pageNumber") Integer pageNumber,
									@RequestParam("pageSize") Integer pageSize) {
		return studentServiceCrud.listStudent(pageNumber, pageSize);
	}
}

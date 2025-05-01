package com.mycom.myapp.service;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;

public interface StudentServiceCrud {
	StudentResultDto listStudent();
	StudentResultDto detailStudent(int id);

	StudentResultDto insertStudent(StudentDto studentDto); // jpa 통해 table에 insert하고 영속화시킨 객체를 return
	StudentResultDto updateStudent(StudentDto studentDto);
	StudentResultDto deleteStudent(int id); // delete는 return이 void

	StudentResultDto countStudent();
	StudentResultDto listStudent(int pageNumber, int pageSize); // pageSize: 한페이지에 보여주는 개수
}

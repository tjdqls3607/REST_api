package com.mycom.myapp.service;

import com.mycom.myapp.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceCrud {
	List<Student> listStudent();
	Optional<Student> detailStudent(int id);
	
	Student insertStudent(Student student); // jpa 통해 table에 insert하고 영속화시킨 객체를 return
	Optional<Student> updateStudent(Student student);
	void deleteStudent(int id); // delete는 return이 void

	long countStudent();
	List<Student> listStudent(int pageNumber, int pageSize); // pageSize: 한페이지에 보여주는 개수
}

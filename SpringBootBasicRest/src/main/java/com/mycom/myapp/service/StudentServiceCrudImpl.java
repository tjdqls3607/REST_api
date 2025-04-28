package com.mycom.myapp.service;

import com.mycom.myapp.entity.Student;
import com.mycom.myapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceCrudImpl implements StudentServiceCrud{

	private final StudentRepository studentRepository;
	
	
	@Override
	public List<Student> listStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> detailStudent(int id) {
		return studentRepository.findById(id); // id기준으로 찾음
	}

	@Override
	public Student insertStudent(Student student) {
//		Student student2 = studentRepository.save(student);
		// 추가적인 영속화된 student2로 비즈니스로직 처리 가능.
		
		return studentRepository.save(student);
		
	}

	@Override
	public Optional<Student> updateStudent(Student student) {
		// 수정 작업일 때, Student 타입으로 return할 경우: jpa에게 알아서 해.
		// => id가 없으면: insert, 있으면: update 됨
//		return studentRepository.save(student); 
		
		// 수정 작업일 때, Optional<Student> 타입으로 return할 경우: 직접 id 검사 후 진행
		Optional<Student> existingStudent = studentRepository.findById(student.getId());
		if(existingStudent.isPresent()) // 존재할 경우
			return Optional.of(studentRepository.save(student));
		
		return Optional.empty();
	}

	@Override
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}

	@Override
	public long countStudent() {
		return studentRepository.count();
	}

	@Override
	public List<Student> listStudent(int pageNumber, int pageSize) {
		// 페이징 관련 information 가진 변수
		Pageable pageable = PageRequest.of(pageNumber, pageSize); 
		
		Page<Student> page = studentRepository.findAll(pageable);
		return page.toList();
	}
	
	
}

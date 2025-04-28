package com.mycom.myapp.service;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.entity.Student;
import com.mycom.myapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceCrudImpl implements StudentServiceCrud{

	private final StudentRepository studentRepository;
	
	
	@Override
	public StudentResultDto listStudent() {

		StudentResultDto studentResultDto = new StudentResultDto();

		try {
			// findAll() -> entity 형식
			List<Student> studentList = studentRepository.findAll();

			// etity -> dto 변환
			List<StudentDto> studentDtoList = new ArrayList<>();
			studentList.forEach(student -> {
				StudentDto studentDto = StudentDto.builder()
						.id(student.getId())
						.name(student.getName())
						.email(student.getEmail())
						.phone(student.getPhone())
						.build();
				studentDtoList.add(studentDto);
			});

			studentResultDto.setStudentList(studentDtoList); // resultdto에 List<dto> 형식으로 정의되어있음. dto 넣어야댐
			studentResultDto.setResult("success");

		}catch (Exception e){
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}
		return studentResultDto;
	}

	@Override
	public StudentResultDto detailStudent(int id) {

		StudentResultDto studentResultDto = new StudentResultDto();

		try {
			// findAll() -> entity 형식
			Optional<Student> optionalStudent = studentRepository.findById(id);

			// ifPresentOrElse(값 존재할 경우, 값 존재안할 경우)
			//
			optionalStudent.ifPresentOrElse(
					student -> { // 값 존재할 때
						StudentDto studentDto = StudentDto.builder()
								.id(student.getId())
								.name(student.getName())
								.email(student.getEmail())
								.phone(student.getPhone())
								.build();
						studentResultDto.setStudentDto(studentDto);
						studentResultDto.setResult("success");
					},
					() -> { // 값 존재 안할 때
						studentResultDto.setResult("not found");
					});

		}catch (Exception e){
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}
		return studentResultDto;
	}

	@Override
	public StudentResultDto insertStudent(StudentDto studentDto) {

		StudentResultDto studentResultDto = new StudentResultDto();
		// dto -> entity
		Student student = Student.builder()
				.name(studentDto.getName())
				.email(studentDto.getEmail())
				.phone(studentDto.getPhone())
				.build();
		try{
			studentRepository.save(student);
			studentResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}

		return studentResultDto;
	}

	@Override
	public StudentResultDto updateStudent(StudentDto studentDto) {
		// findById 처리하지 않는 버전 ㄱ
		StudentResultDto studentResultDto = new StudentResultDto();
		// dto -> entity
		Student student = Student.builder()
				.id(studentDto.getId())
				.name(studentDto.getName())
				.email(studentDto.getEmail())
				.phone(studentDto.getPhone())
				.build();
		try{
			studentRepository.save(student);
			studentResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}

		return studentResultDto;
	}

	@Override
	public StudentResultDto deleteStudent(int id) {
		// findById 처리하지 않는 버전 ㄱ
		StudentResultDto studentResultDto = new StudentResultDto();

		try{
			studentRepository.deleteById(id);
			studentResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}

		return studentResultDto;
	}

	@Override
	public StudentResultDto countStudent() {
		// findById 처리하지 않는 버전 ㄱ
		StudentResultDto studentResultDto = new StudentResultDto();

		try{
			Long count = studentRepository.count();
			studentResultDto.setCount(count);
			studentResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}

		return studentResultDto;
	}

	@Override
	public StudentResultDto listStudent(int pageNumber, int pageSize) {

		StudentResultDto studentResultDto = new StudentResultDto();

		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Student> page = studentRepository.findAll(pageable);
			List<Student> studentList = page.toList(); // entity

			// etity -> dto 변환
			List<StudentDto> studentDtoList = new ArrayList<>();

			studentList.forEach(student -> {
				StudentDto studentDto = StudentDto.builder()
						.id(student.getId())
						.name(student.getName())
						.email(student.getEmail())
						.phone(student.getPhone())
						.build();
				studentDtoList.add(studentDto);
			});

			studentResultDto.setStudentList(studentDtoList); // resultdto에 List<dto> 형식으로 정의되어있음. dto 넣어야댐
			studentResultDto.setResult("success");

		}catch (Exception e){
			e.printStackTrace();
			studentResultDto.setResult("fail");
		}
		return studentResultDto;
	}
	
	
}

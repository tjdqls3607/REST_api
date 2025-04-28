package com.mycom.myapp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.entity.Student;
import com.mycom.myapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class StudentServiceCrudImpl implements StudentServiceCrud{
    private final StudentRepository studentRepository;
    
    @Override
    public StudentResultDto listStudent() {
        
        StudentResultDto studentResultDto = new StudentResultDto();
        
        try {
            List<Student> studentList = studentRepository.findAll();
            List<StudentDto> studentDtoList = new ArrayList<>();
            
            studentList.forEach( student -> {
                StudentDto studentDto = StudentDto.builder()
                                .id(student.getId())
                                .name(student.getName())
                                .email(student.getEmail())
                                .phone(student.getPhone())
                                .build();
                studentDtoList.add(studentDto);
            });
            
            studentResultDto.setStudentList(studentDtoList);
            studentResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            studentResultDto.setResult("fail");
        }
        return studentResultDto;
    }
    @Override
    public StudentResultDto detailStudent(int id) {
        
        StudentResultDto studentResultDto = new StudentResultDto();
        
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            
            optionalStudent.ifPresentOrElse(
                student -> {
                    StudentDto studentDto = StudentDto.builder()
                                    .id(student.getId())
                                    .name(student.getName())
                                    .email(student.getEmail())
                                    .phone(student.getPhone())
                                    .build();
                    studentResultDto.setStudentDto(studentDto);
                    studentResultDto.setResult("success");
                }, 
                () -> {
                    studentResultDto.setResult("notfound");
                }
            );
        }catch(Exception e) {
            e.printStackTrace();
            studentResultDto.setResult("fail");
        }
        return studentResultDto;
    }
    @Override
    public StudentResultDto insertStudent(StudentDto studentDto) {
        StudentResultDto studentResultDto = new StudentResultDto();
        Student student = Student.builder()
        		.name(studentDto.getName())
        		.email(studentDto.getEmail())
        		.phone(studentDto.getPhone())
        		.build();
        try {
            studentRepository.save(student);
            studentResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            studentResultDto.setResult("fail");
        }
        return studentResultDto;
    }
    @Override
    public StudentResultDto updateStudent(StudentDto studentDto) {
        // findById 처리 X 버전
        StudentResultDto studentResultDto = new StudentResultDto();
        Student student = Student.builder()
        		.id(studentDto.getId()) // jpa 에서 update
        		.name(studentDto.getName())
        		.email(studentDto.getEmail())
        		.phone(studentDto.getPhone())
        		.build();
        try {
            studentRepository.save(student);
            studentResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            studentResultDto.setResult("fail");
        }
        return studentResultDto;
    }
    @Override
    public StudentResultDto deleteStudent(int id) {
        
        // findById 처리 X 버전
        StudentResultDto studentResultDto = new StudentResultDto();
        try {
            studentRepository.deleteById(id);
            studentResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            studentResultDto.setResult("fail");
        }
        return studentResultDto;
    }
    @Override
    public StudentResultDto countStudent() {
        
        StudentResultDto studentResultDto = new StudentResultDto();
        try {
            Long count = studentRepository.count();
            studentResultDto.setCount(count);
            studentResultDto.setResult("success");
        }catch(Exception e) {
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
            List<Student> studentList = page.toList();
            
            List<StudentDto> studentDtoList = new ArrayList<>();
            
            studentList.forEach( student -> {
                StudentDto studentDto = StudentDto.builder()
                                .id(student.getId())
                                .name(student.getName())
                                .email(student.getEmail())
                                .phone(student.getPhone())
                                .build();
                studentDtoList.add(studentDto);
            });
            
            studentResultDto.setStudentList(studentDtoList);
            studentResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            studentResultDto.setResult("fail");
        }
        return studentResultDto;
    }
}
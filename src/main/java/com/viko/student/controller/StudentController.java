package com.viko.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viko.student.exception.ResourceNotFoundException;
import com.viko.student.model.Student;
import com.viko.student.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@GetMapping("")
	public List<Student> getAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	@PostMapping("/create")
	public Student createStudent(@Valid @RequestBody Student student, String nim) {
		return studentRepository.save(student);
	}

	@GetMapping("get/{id}")
	public Student getStudentById(@PathVariable(value = "id") Integer studentId) {
		return studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
	}

	@PutMapping("update/{id}")
	public Student updateStudent(@PathVariable(value = "id") Integer studentId,
			@Valid @RequestBody Student studentDetails) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
		student.setNama(studentDetails.getNama());
		student.setNilai(studentDetails.getNilai());
		student.setNim(studentDetails.getNim());
		Student updatedStudent = studentRepository.save(student);
		return updatedStudent;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Integer studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
		studentRepository.delete(student);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/nilai/max")
	public Integer getNilaiMax() {
		Integer student = studentRepository.getNilaiMax();
		return student;
	}

	@GetMapping("/nilai/average")
	public Integer getNilaiAverage() {
		Integer student = studentRepository.getNilaiAverage();
		return student;
	}
}
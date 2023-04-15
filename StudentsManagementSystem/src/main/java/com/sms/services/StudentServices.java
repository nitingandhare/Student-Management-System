package com.sms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.entities.Student;

public interface StudentServices {
	List<Student> getAllStudent();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	void deleteStudentById(Long id);
}

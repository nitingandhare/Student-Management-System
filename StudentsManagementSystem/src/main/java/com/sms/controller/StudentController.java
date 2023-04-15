package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entities.Student;
import com.sms.services.StudentServices;

@Controller
public class StudentController {

	@Autowired
	StudentServices studentServices;

//	Handler method to handle list students and return mode and view
	public StudentController(StudentServices studentServices) {
		super();
		this.studentServices = studentServices;
	}

	@GetMapping("/students")
	public String listStudent(Model model) {
		model.addAttribute("students", studentServices.getAllStudent());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
//		Create Student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentServices.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id,Model model) {
		model.addAttribute("student", studentServices.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
//		get student from database by id
		Student existingStudent = studentServices.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setAddress(student.getAddress());
		
//		save updated student object
		studentServices.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
//	Handler method to delete student
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentServices.deleteStudentById(id);
		return "redirect:/students";
	}
}
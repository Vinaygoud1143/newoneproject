package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.exception.StudentException;
import com.example.service.Studentservice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class Studentcontroller {

	@Autowired
	Studentservice studentservice;

	@ApiOperation("this is api operation for getting the all student details")
	@GetMapping("/students/getall")
	public ResponseEntity<?> GetAllStudents() {
		List<Student> list = new ArrayList<Student>();
		try {
			list = studentservice.getall();
			if (list.size() == 0) {
				throw new StudentException("no student records  found please add the records");
			}
			return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		} catch (StudentException e) {
			return new ResponseEntity<String>(e.message, HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation("this api for getting particular student deatils")
	@GetMapping(value = "/students/{id}")
	public ResponseEntity<?> GetStudenteById(@ApiParam(value="give id to get student details",required = true)  @PathVariable Integer id)

	{
		try {

			Student entity = studentservice.findstudent(id);

			return new ResponseEntity<Student>(entity, HttpStatus.OK);

		} catch (StudentException ex) {
			return new ResponseEntity<String>(ex.message, HttpStatus.NOT_FOUND);

		}

	}

	@ApiOperation("this api for update the student details or create student  if student not exist ")
	@PostMapping("/students")
	public ResponseEntity<?> CreateOrUpdateStudent(@ApiParam(value="give student detals",required = true)@RequestBody Student student) {
		
		try {
		Student updated = studentservice.updatesavestudent(student);
		return new ResponseEntity<Student>(updated, HttpStatus.OK);}
		catch(Exception e) {
			
			return new ResponseEntity<String>("please give correct student details", HttpStatus.OK);	
		}
	}

	@ApiOperation("this api for deleting student for given studentn id")
	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> DeleteStudenteById(@ApiParam(value="please give student id ",required = true)@PathVariable("id") Integer id) {

		try {
			studentservice.deleteEmployeeById(id);

			return new ResponseEntity<String>("deleted succesufull", HttpStatus.OK);
		} catch (StudentException e) {
			return new ResponseEntity<String>(e.message, HttpStatus.NOT_FOUND);
		}
	}

}

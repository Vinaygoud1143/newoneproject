package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.exception.StudentException;
import com.example.irepository.IStudentrepository;

@Service
@Component
public class Studentservice {

	@Autowired
	IStudentrepository studentrepo;

	public List<Student> getall() {
		List<Student> alllist = new ArrayList<Student>();
	
		alllist = studentrepo.findAll();
		
		return alllist;
	}

	public Student findstudent(Integer id) throws StudentException {
		Optional<Student> student = studentrepo.findById(id);
		
		if(!student.isPresent()) {
			
			throw new StudentException("student is not found with id:"+id);
		}
		Student s = student.get();
		return s;
	}

	public Student updatesavestudent(Student student) {
		Optional<Student> studentone = studentrepo.findById(student.getSno());
		if (studentone.isPresent()) {
			Student student1 = studentone.get();

			student1.setSname(student1.getSname());
			student1.setSaddress(student1.getSaddress());
			student1.setSdepartment(student1.getSdepartment());
			student1 = studentrepo.save(student1);
			return student1;
		} else {
			Student newstudent = studentrepo.save(student);
			return newstudent;
		}
	}

	public void deleteEmployeeById(Integer id) throws StudentException {
		Optional<Student> employee = studentrepo.findById(id);

		if (employee.isPresent()) {
			studentrepo.deleteById(id);
		} 
		else {
            throw new StudentException("no student record found to delete with the id:"+id);
		}

	}
	
}

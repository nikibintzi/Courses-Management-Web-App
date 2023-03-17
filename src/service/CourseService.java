package springbootthymeleaf.service;

import java.util.List;

import springbootthymeleaf.entity.Course;


public interface CourseService {

	public List<Course> findAll();
	public List<Course> findByProfUsername(String username);
	
	public Course findById(int theId);
	
	public void save(Course newCourse);
	
	public void deleteById(int theId);
	
}

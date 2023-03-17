package springbootthymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbootthymeleaf.entity.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer>{
	public Course findById(int id);
	public Course deleteById(int id);
	public List<Course> findByProfUsername(String username);
}

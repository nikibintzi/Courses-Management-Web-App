package springbootthymeleaf_tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import springbootthymeleaf.SpringBootThymeleafApplication;
import springbootthymeleaf.dao.CourseDAO;
import springbootthymeleaf.entity.Course;


@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
class TestCourseDAOJpa {
	@Autowired 
	CourseDAO courseDAO;
			
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}

	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = courseDAO.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("MYZ100", storedCourse.getName(),"Course name must be Linear Algebra 1");
	}
	
	@Test
	public void testFindByProfUsernameReturnsList() {
	    List<Course> courses = courseDAO.findByProfUsername("zarras");
	    Assertions.assertNotNull(courses);
	    Assertions.assertTrue(courses.size()>0,() ->"Size is greater than 0");
	    Assertions.assertEquals(3,courses.size(),"Size is not 3");
	}
	
	@Test
	public void testUpdateCourse() {
		Course storedCourse = courseDAO.findById(5);
		storedCourse.setYear(5);
	     
		courseDAO.save(storedCourse);
	     
	    Course updCourse = courseDAO.findById(5);
	    Assertions.assertEquals(5,updCourse.getYear(),"Year is not 5.Not updated");
	}
	
	@Test
	public void testDeleteCourse() {
		Course storedCourse = courseDAO.findById(4);
		courseDAO.deleteById(storedCourse.getId());
	    Course deletedCourse = courseDAO.findById(4);
	    Assertions.assertNull(deletedCourse);       
	     
	}
}


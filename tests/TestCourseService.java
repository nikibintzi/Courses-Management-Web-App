package springbootthymeleaf_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import springbootthymeleaf.SpringBootThymeleafApplication;
import springbootthymeleaf.entity.Course;
import springbootthymeleaf.service.CourseService;


@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
class TestCourseService {

	@Autowired 
	CourseService courseService;
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(courseService);
	}

	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = courseService.findById(2);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("MYX101", storedCourse.getName());
	}
}

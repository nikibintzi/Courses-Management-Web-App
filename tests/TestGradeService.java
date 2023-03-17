package springbootthymeleaf_tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import springbootthymeleaf.SpringBootThymeleafApplication;
import springbootthymeleaf.entity.Grade;
import springbootthymeleaf.entity.RegistrationId;
import springbootthymeleaf.service.GradeService;



@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
class TestGradeService {

	@Autowired 
	GradeService gradeService;
	
	RegistrationId regId;
	
	@BeforeEach                                         
    void setUp() {
		regId = new RegistrationId(4424,3);
    }
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(gradeService);
	}
	
	@Test
	void testFindByRegIdReturnsGrade() {
		Grade storedGrade = gradeService.findByRegId(regId);
		Assertions.assertNotNull(storedGrade);
		Assertions.assertEquals(5, storedGrade.getProject(),"Project grade should be 5");
		Assertions.assertEquals(6, storedGrade.getFinalExam(),"Final exam grade should be 6");
	}
		
	@Test
	public void testFindAllByRegId_CourseId() {
		List<Grade> studentsGrades = gradeService.findAllByRegId_CourseId(3);
		Assertions.assertNotNull(studentsGrades);
		Assertions.assertTrue(studentsGrades.size()>0,() ->"Size is less than 0");
		Assertions.assertEquals(1,studentsGrades.size(),"Size is not 1");
	}
}

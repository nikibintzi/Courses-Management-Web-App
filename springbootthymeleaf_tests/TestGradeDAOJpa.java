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
import springbootthymeleaf.dao.GradeDAO;
import springbootthymeleaf.entity.Grade;
import springbootthymeleaf.entity.RegistrationId;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
class TestGradeDAOJpa {

	@Autowired 
	GradeDAO gradeDAO;
	
	RegistrationId regId;
	
	@BeforeEach                                         
    void setUp() {
		regId = new RegistrationId(4435,1);
    }
	
	@Test
	void testGradeDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(gradeDAO);
	}

	
	@Test
	void testFindByRegIdReturnsGrade() {
		Grade storedGrade = gradeDAO.findByRegId(regId);
		Assertions.assertNotNull(storedGrade);
		Assertions.assertEquals(5, storedGrade.getProject(),"Project grade should be 5");
		Assertions.assertEquals(5, storedGrade.getFinalExam(),"Final exam grade should be 5");
	}
	
		
	@Test
	public void testFindAllByRegId_CourseId() {
		List<Grade> studentsGrades = gradeDAO.findAllByRegId_CourseId(1);
		Assertions.assertNotNull(studentsGrades);
		Assertions.assertTrue(studentsGrades.size()>0,() ->"Size is less than 0");
		Assertions.assertEquals(1,studentsGrades.size(),"Size is not 1");
	}
	
	
	
	
}

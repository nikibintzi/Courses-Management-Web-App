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
import springbootthymeleaf.entity.Registration;
import springbootthymeleaf.entity.RegistrationId;
import springbootthymeleaf.service.RegistrationService;



@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
class TestRegistrationService {

	@Autowired 
	RegistrationService regService;
	
	RegistrationId regId;
	
	@BeforeEach                                         
    void setUp() {
		regId = new RegistrationId(4444,5);
    }
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(regService);
	}
	
	@Test
	void testFindByRegIdReturnsRegistration() {
		Registration storedReg = regService.findByRegId(regId);
		Assertions.assertNotNull(storedReg);
		Assertions.assertEquals("Goofy", storedReg.getStudentName(),"Student name should be Goofy");
	}
		
	@Test
	public void testFindAllByRegId_CourseId() {
		List<Registration> students = regService.findAllByRegId_CourseId(5);
		Assertions.assertNotNull(students);
		Assertions.assertTrue(students.size()>0,() ->"Size is less than 0");
		Assertions.assertEquals(1,students.size(),"Size is not 1");
	}
}

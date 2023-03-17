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
import springbootthymeleaf.dao.RegistrationDAO;
import springbootthymeleaf.entity.Registration;
import springbootthymeleaf.entity.RegistrationId;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
class TestRegistrationDAOJpa {

	@Autowired 
	RegistrationDAO regDAO;
	
	RegistrationId regId;
		
	@BeforeEach                                         
    void setUp() {
		regId = new RegistrationId(4435,1);
    }
	
	@Test
	void testRegistrationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(regDAO);
	}

	
	@Test
	void testFindByRegIdReturnsRegistration() {
		Registration storedReg = regDAO.findByRegId(regId);
		Assertions.assertNotNull(storedReg);
		Assertions.assertEquals("Niki", storedReg.getStudentName(),"Student name should be Niki");
	}
		
	@Test
	public void testFindAllByRegId_CourseId() {
		List<Registration> students = regDAO.findAllByRegId_CourseId(1);
		Assertions.assertNotNull(students);
		Assertions.assertTrue(students.size()>0,() ->"Size is less than 0");
		Assertions.assertEquals(1,students.size(),"Size is not 1");
	}
}



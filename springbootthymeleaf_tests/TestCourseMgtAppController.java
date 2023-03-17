package springbootthymeleaf_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import springbootthymeleaf.SpringBootThymeleafApplication;
import springbootthymeleaf.controller.CourseMgtAppController;
import springbootthymeleaf.entity.Course;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@ContextConfiguration(classes = SpringBootThymeleafApplication.class)
@AutoConfigureMockMvc
class TestCourseMgtAppController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CourseMgtAppController courseMgtAppController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }

	@Test
	void testCourseMgtAppControllerIsNotNull() {
		Assertions.assertNotNull(courseMgtAppController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}

	@WithMockUser(value = "zarras")
	@Test 
	void testSaveCourseReturnsPage() throws Exception {
		
	    Course course = new Course(100, "Ethics", "Ethics in Machine Learning", 5, 9, "Bias in algorithms", "zarras", null);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(course.getId()));
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("syllabus",course.getSyllabus());
	    multiValueMap.add("year", Integer.toString(course.getYear()));
	    multiValueMap.add("semester",Integer.toString(course.getSemester()));
	    multiValueMap.add("description", course.getDescription());
	    
		mockMvc.perform(
				post("/saveCourse").
			    params(multiValueMap)).
				andExpect(status().isFound()).
				andExpect(view().name("redirect:/list"));	
	}
	

	
}

package springbootthymeleaf.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springbootthymeleaf.entity.Course;
import springbootthymeleaf.entity.Grade;
import springbootthymeleaf.entity.Registration;
import springbootthymeleaf.entity.RegistrationId;
import springbootthymeleaf.service.CourseService;
import springbootthymeleaf.service.GradeService;
import springbootthymeleaf.service.RegistrationService;
import org.springframework.security.core.Authentication;

@Controller
public class CourseMgtAppController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private GradeService gradeService;
	
	public CourseMgtAppController(CourseService theCourseService, RegistrationService theRegistrationService, GradeService theGradeService) {
		courseService = theCourseService;
		registrationService=theRegistrationService;
		gradeService=theGradeService;
	}
	
	@GetMapping("/list") 
	public String getAllCourses(Authentication authentication, Model theModel) {
		String professor=authentication.getName();
		List<Course> allCourses = courseService.findByProfUsername(professor);
		theModel.addAttribute("courses", allCourses);
		return "courses/list-courses";
	}
	
	@GetMapping("/addCourseForm")
	public String addCourseForm(Authentication authentication,Model theModel) {
		String professor=authentication.getName();
		Course newCourse = new Course();
		newCourse.setProfUsername(professor);
		theModel.addAttribute("course", newCourse);
		return "courses/add-course-form";
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course Course,Model theModel) {
		courseService.save(Course);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateFormForCourses")
	public String showUpdateFormForCourses(@RequestParam("courseId") int anId,Model theModel) {
		Course theCourse = courseService.findById(anId);
		theModel.addAttribute("course", theCourse);
		return "courses/add-course-form";
	}
	
	
	@GetMapping("/deleteCourse")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		List <Registration> students=registrationService.findAllByRegId_CourseId(theId);
		if (students.size()!=0) {
			registrationService.deleteAllByRegId_CourseId(theId);
        } 
		courseService.deleteById(theId);
		return "redirect:/list";
	}
	
	@GetMapping("/listRegistrationsPerCourse")
	public String getAllRegistrations(@RequestParam("courseId") int courseId,Model theModel) {
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("registrations", registrationService.findAllByRegId_CourseId(courseId));
		return "/students/list-registrations";
	}
	
	
	
	@GetMapping("/addStudentForm")
	public String addStudentForm(@RequestParam("courseId") int courseId, Model theModel) {
		RegistrationId regId= new RegistrationId();
		regId.setCourseId(courseId);
		Registration registration = new Registration();
		registration.setRegId(regId);
		regId=registration.getRegId();
		Course theCourse = courseService.findById(courseId);
		List<Registration> alist =theCourse.getRegistrations();
		alist.add(registration);
		theCourse.setRegistrations(alist);
		theModel.addAttribute("registration",registration);
		return "/students/add-registration-form";
	} 
	

	@Transactional
	@PostMapping("/saveRegistration")
	public String saveRegistration(@ModelAttribute("registration") Registration registration, Model theModel) {
		registrationService.save(registration); 
		return "redirect:/list"; 
	}
	
	@GetMapping("/showUpdateFormForReg")
	public String showUpdateFormForReg(@RequestParam int studentId, @RequestParam int courseId, Model theModel) {
		RegistrationId theRegId= new RegistrationId(studentId,courseId);
		Registration theRegistration = registrationService.findByRegId(theRegId);
		/*if (theRegistration!=null) {
			RegistrationId regId=theRegistration.getRegId();
		}else {
			System.out.println("Error! A student with that id doesn't exist.");
		}*/
		theModel.addAttribute("registration",theRegistration);
		return "/students/edit-registration-form";
	}
	
	
	@PostMapping("/editRegistration")
	public String editRegistration(@ModelAttribute("registration") Registration registration, BindingResult result, Model theModel) {
		if (result.hasErrors()) {
			System.out.println("Error occured during update! Abort.");
			return "error";
        }
		RegistrationId regId=registration.getRegId();
		Registration theRegistration = registrationService.findByRegId(regId);
		if (theRegistration!=null) {
			theRegistration.setStudentName(registration.getStudentName());
			theRegistration.setYear_of_reg(registration.getYear_of_reg());
			theRegistration.setCur_semester(registration.getCur_semester());
			registrationService.save(theRegistration); 
			
		}else {
			System.out.println("Error! A student with that id doesn't exist.");
		}
		return "redirect:/list";
	}

	@GetMapping("/deleteRegistration")
	public String deleteRegistration(@RequestParam int studentId, @RequestParam int courseId ) {
		RegistrationId theRegId= new RegistrationId(studentId,courseId);
		registrationService.deleteByRegId(theRegId);
		return "redirect:/list"; 
	}

	@GetMapping("/gradeStudent")
	public String gradeStudent(@RequestParam int studentId, @RequestParam int courseId, Model theModel) {
		RegistrationId theRegId= new RegistrationId(studentId,courseId);
		Grade grade= new Grade();
		grade.setRegId(theRegId);
		grade.setOverallGrade(0);
		theModel.addAttribute("grade",grade);
		return "/grades/register-grades";
	}
	
	@PostMapping("/saveGrade")
	public String saveGrade(@ModelAttribute("grade") Grade grade, Model theModel) {
		gradeService.save(grade); 
		return "redirect:/list";//list-grades"; 
	}
	
	@GetMapping("/listGrades")
	public String listGrades(@ModelAttribute("courseId") int id, Model theModel) { 
	
		List<Grade> allGradesOfCourse = gradeService.findAllByRegId_CourseId(id);
		theModel.addAttribute("courseId", id);
		theModel.addAttribute("grades", allGradesOfCourse);
		return "/grades/list-grades";
	}
	
	@GetMapping("/calcOverallGrade")
	public String calcOverallGrade(@ModelAttribute("courseId") int id, Model theModel) { 
	
		double sum=0;
		List<Grade> allGrades = gradeService.findAllByRegId_CourseId(id);
		int counter=allGrades.size();  
		for (Grade i : allGrades)   
		{  
			int projectGr= i.getProject();
			int finExamGr= i.getFinalExam();
			double total= 0.4*projectGr + 0.6*finExamGr;
			i.setOverallGrade(total);
			sum+=total;
		}
		sum=sum/counter;
		theModel.addAttribute("courseId", id);
		theModel.addAttribute("averageGrade",sum); 
		theModel.addAttribute("grades",allGrades);
		 
		return "/grades/overall-grades";
	}
	
	@GetMapping("/deleteGrade")
	public String deleteGrade(@RequestParam int studentId, @RequestParam int courseId ) {
		RegistrationId theRegId= new RegistrationId(studentId,courseId);
		gradeService.deleteByRegId(theRegId);
		return "redirect:/list"; 
	}

}

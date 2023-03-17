package springbootthymeleaf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import springbootthymeleaf.controller.StatisticsStrategy;
import springbootthymeleaf.dao.CourseDAO;
import springbootthymeleaf.entity.Course;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	public CourseDAO courseRepository;
	
	//private List<StatisticsStrategy> startCalcStrategies;
	
	public CourseServiceImpl() {
		super();
	}
	
	@Autowired
	public CourseServiceImpl(CourseDAO theCourseRepository) {
		courseRepository=theCourseRepository;
	}

	
	@Override
	@Transactional
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	@Transactional
	public List<Course> findByProfUsername(String username){
		return courseRepository.findByProfUsername(username);
		
	}
	
	@Override
	@Transactional
	public Course findById(int theId) {
		Course courseWithThatID =courseRepository.findById(theId);
		
		if (courseWithThatID != null ) {
			return courseWithThatID;
		}
		else {
			// we didn't find the course
			throw new RuntimeException("Did not find course with id : " + theId);
		}
	}
	
	@Override
	@Transactional
	public void save(Course newCourse) {
		courseRepository.save(newCourse);
		
	}

	@Override
	//@Transactional
	public void deleteById(int theId) {
		courseRepository.deleteById(theId);
	}

	/*public List<StatisticsStrategy> getStatCalcStrategies() {
		return startCalcStrategies;
	}

	public List<StatisticsStrategy> getStartCalcStrategies() {
		return startCalcStrategies;
	}

	public void setStartCalcStrategies(List<StatisticsStrategy> startCalcStrategies) {
		this.startCalcStrategies = startCalcStrategies;
	}*/
	
}

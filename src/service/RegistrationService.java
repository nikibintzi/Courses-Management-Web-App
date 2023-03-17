package springbootthymeleaf.service;

import java.util.List;

import springbootthymeleaf.entity.Registration;
import springbootthymeleaf.entity.RegistrationId;



public interface RegistrationService {

	public List<Registration> findAll();
	
	public List<Registration> findAllByRegId_CourseId(int id);
	
	public void save(Registration newRegistration);
	
	public void deleteByRegId(RegistrationId regId);
	
	public void deleteAllByRegId_CourseId(int id);
	
	public Registration findByRegId(RegistrationId regId);
}

package springbootthymeleaf.service;


import java.util.List;

import springbootthymeleaf.entity.Grade;
import springbootthymeleaf.entity.RegistrationId;

public interface GradeService {

	public List<Grade> findAll();
	public void save(Grade newGrade);
	public Grade findByRegId(RegistrationId regId);
	public void deleteByRegId(RegistrationId regId);
	public List<Grade> findAllByRegId_CourseId(int id);
}

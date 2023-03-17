package springbootthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springbootthymeleaf.dao.GradeDAO;
import springbootthymeleaf.entity.Grade;
import springbootthymeleaf.entity.RegistrationId;

@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	public GradeDAO gradeDAO;
		
		
	@Autowired
	public GradeServiceImpl(GradeDAO theGradeRepository) {
		gradeDAO=theGradeRepository;
	}
		
		
	@Override
	@Transactional
	public void save(Grade newGrade) {
		gradeDAO.save(newGrade);
	}


	@Override
	public Grade findByRegId(RegistrationId regId) {
		Grade gradeWithThatID =gradeDAO.findByRegId(regId);
		if (gradeWithThatID != null ) {
			return gradeWithThatID;
		}
		else {
			// we didn't find the grade
			throw new RuntimeException("Did not find grading.");
		}
	}


	@Override
	public List<Grade> findAll() {
		return gradeDAO.findAll();
		
	}


	@Override
	public void deleteByRegId(RegistrationId regId) {
		gradeDAO.deleteByRegId(regId);
	}


	@Override
	public List<Grade> findAllByRegId_CourseId(int id) {
		List<Grade> gradesWithThatID =gradeDAO.findAllByRegId_CourseId(id);
		return gradesWithThatID;
	}

}


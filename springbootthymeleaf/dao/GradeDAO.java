package springbootthymeleaf.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbootthymeleaf.entity.Grade;
import springbootthymeleaf.entity.RegistrationId;

@Repository
public interface GradeDAO extends JpaRepository<Grade, Integer>{

	public Grade findByRegId(RegistrationId regId);
	public void deleteByRegId(RegistrationId regId);
	public List<Grade> findAllByRegId_CourseId(int id);
}

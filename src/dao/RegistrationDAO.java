package springbootthymeleaf.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springbootthymeleaf.entity.Registration;
import springbootthymeleaf.entity.RegistrationId;

@Repository
public interface RegistrationDAO extends JpaRepository<Registration, Integer>{
	
	public void deleteByRegId(RegistrationId regId);
	@Transactional
	public void deleteAllByRegId_CourseId(int id);
	public Registration findByRegId(RegistrationId regId);
	
	public List<Registration> findAllByRegId_CourseId(int id);
}

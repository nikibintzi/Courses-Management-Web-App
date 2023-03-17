package springbootthymeleaf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootthymeleaf.dao.RegistrationDAO;
import springbootthymeleaf.entity.Registration;
import springbootthymeleaf.entity.RegistrationId;


@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	public RegistrationDAO regRepository;
	
	public RegistrationServiceImpl() {
		super();
	}
	
	@Autowired
	public RegistrationServiceImpl(RegistrationDAO theRegRepository) {
		regRepository=theRegRepository;
	}
	
	@Override
	@Transactional
	public List<Registration> findAll() {
		return regRepository.findAll();
	}

	
	@Override
	@Transactional
	public void save(Registration newRegistration) {
		regRepository.save(newRegistration);
	}
	
	@Override
	public Registration findByRegId(RegistrationId regId) {
		Registration regWithThatID =regRepository.findByRegId(regId);
		if (regWithThatID != null ) {
			return regWithThatID;
		}
		else {
			// we didn't find the reg
			throw new RuntimeException("Did not find student registration.");
		}
	}
	
	@Override
	@Transactional
	public void deleteByRegId(RegistrationId regId) {
		regRepository.deleteByRegId(regId);
	}

	@Override
	public List<Registration> findAllByRegId_CourseId(int id) {
		return regRepository.findAllByRegId_CourseId(id);
		}

	@Override
	public void deleteAllByRegId_CourseId(int id) {
		regRepository.deleteAllByRegId_CourseId(id);
		
	}





}

package springbootthymeleaf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int id;
	
	@Column(name = "course_name")
	private String name;
	
	@Column(name = "syllabus")
	private String syllabus;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "semester")
	private int semester;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "prof_username")
	private String profUsername;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Registration> registrations;
	
	public Course() {}

	public Course(int id, String name, String syllabus, int year, int semester, String description, String username,
			List<Registration> registrations) {
		this.id = id;
		this.name = name;
		this.syllabus = syllabus;
		this.year = year;
		this.semester = semester;
		this.description = description;
		this.profUsername = username;
		this.registrations = registrations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getProfUsername() {
		return profUsername;
	}

	public void setProfUsername(String profUsername) {
		this.profUsername = profUsername;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
	


}

package springbootthymeleaf.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "registrations")
public class Registration {

	@EmbeddedId
	private RegistrationId regId;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "year_of_reg")
	private int year_of_reg;
	
	@Column(name = "cur_semester")
	private int cur_semester;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String newStudentName) {
		this.studentName = newStudentName;
	}

	public int getYear_of_reg() {
		return year_of_reg;
	}

	public void setYear_of_reg(int year_of_reg) {
		this.year_of_reg = year_of_reg;
	}

	public int getCur_semester() {
		return cur_semester;
	}

	public void setCur_semester(int cur_semester) {
		this.cur_semester = cur_semester;
	}

	public RegistrationId getRegId() {
		return regId;
	}

	public void setRegId(RegistrationId regId) {
		this.regId = regId;
	}

	public Registration() {	}
	
	public Registration(RegistrationId regId, String studentName, int year_of_reg, int cur_semester) {
		this.regId = regId;
		this.studentName = studentName;
		this.year_of_reg = year_of_reg;
		this.cur_semester = cur_semester;
	}
	
	public int getStudentIdFromRegId() {
		return regId.getStudentId();//studentId;
	}
	
	public int getCourseIdFromRegId() {
		return regId.getCourseId();
	}
	
	/*
	public int getCourseId() {
		//courseId=course.getId();
		return courseId;
	}

	public void setCourseId(int aCourseId) {
		//courseId=aCourseId;
		this.courseId=aCourseId;
	}
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}*/
	
}

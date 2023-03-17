package springbootthymeleaf.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable 
public class RegistrationId implements Serializable{
	
	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "course_id")
	private int courseId;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public RegistrationId() {
	}
	
	public RegistrationId(int studentId, int courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}

	


}

package springbootthymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {

	@EmbeddedId
	private RegistrationId regId;
	
	@Column(name = "project_grade")
	private int project;
	
	@Column(name = "final_exam_grade")
	private int finalExam;

	@Column(name = "overall_grade")
	private double overallGrade;
	
	@OneToOne
	@JoinColumns({
		  @JoinColumn(name = "course_id"),
		  @JoinColumn(name = "student_id", insertable = true, updatable = false)
		})
	private Registration student_reg;
	
	public Grade() {
	}

	public Grade(RegistrationId regId, int project, int finalExam, double overallGrade, Registration student_reg) {
		super();
		this.regId = regId;
		this.project = project;
		this.finalExam = finalExam;
		this.overallGrade = overallGrade;
		this.student_reg = student_reg;
	}

	public RegistrationId getRegId() {
		return regId;
	}

	public void setRegId(RegistrationId regId) {
		this.regId = regId;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public int getFinalExam() {
		return finalExam;
	}

	public void setFinalExam(int finalExam) {
		this.finalExam = finalExam;
	}

	public double getOverallGrade() {
		return overallGrade;
	}

	public void setOverallGrade(double overallGrade) {
		this.overallGrade = overallGrade;
	}

	public Registration getStudent_reg() {
		return student_reg;
	}

	public void setStudent_reg(Registration student_reg) {
		this.student_reg = student_reg;
	}
	

	
	
}

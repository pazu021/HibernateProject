package org.example.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue
	private Long studentId;

	@Column(nullable = false)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date birthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentId")
	private StudentDetail studentDetail;

	@ManyToOne(cascade = CascadeType.ALL)
	private Department department;

	@ManyToMany(cascade = CascadeType.ALL)
	//Could add the below annotation to manually define the mapping table for Student and Course. Or just ignore the below annotation to let it be created automatically 
	@JoinTable(name = "Student_Course", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	private Set<Course> courses = new HashSet<Course>(0);

	public Student() {
	}

	public Student(String name, Date birthDate, Date createdDate,
			Date modifiedDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}

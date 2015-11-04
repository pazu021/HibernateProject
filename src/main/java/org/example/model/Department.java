package org.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

	@Id
	private String code;

	private String name;

	/*
	 * When an Employee entity object is removed, the remove operation is
	 * cascaded to the referenced Address entity object. In this regard,
	 * orphanRemoval=true and cascade=CascadeType.REMOVE are identical, and if
	 * orphanRemoval=true is specified, CascadeType.REMOVE is redundant.
	 * 
	 * The difference between the two settings is in the response to
	 * disconnecting a relationship. For example, such as when setting the
	 * address field to null or to another Address object.
	 * 
	 * If orphanRemoval=true is specified the disconnected Address instance is
	 * automatically removed. This is useful for cleaning up dependent objects
	 * (e.g. Address) that should not exist without a reference from an owner
	 * object (e.g. Employee).
	 * 
	 * If only cascade=CascadeType.REMOVE is specified, no automatic action is
	 * taken since disconnecting a relationship is not a remove operation.
	 */

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	// @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true,
	// mappedBy="department")
	private Set<Student> students = new HashSet<Student>(0);

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}

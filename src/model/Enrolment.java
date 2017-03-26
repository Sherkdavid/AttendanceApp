package model;

import java.time.LocalDate;

public class Enrolment {
	private String student_id,class_id;
	private LocalDate date_of;
	
	public Enrolment(String student, String class_id, LocalDate date)
	{
		this.student_id = student;
		this.class_id = class_id;
		date_of = date;
	}

	public String getStudent_id() {
		return student_id;
	}

	public String getClass_id() {
		return class_id;
	}

	public LocalDate getDate_of() {
		return date_of;
	}
}

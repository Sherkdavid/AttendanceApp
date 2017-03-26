package model;

import java.time.LocalDate;

public class Absence
{
	LocalDate date;
	String student_id, class_id;
	boolean valid;
	
	public Absence(String student, String class_id,LocalDate date)
	{
		setValid(false);
		setStudent(student_id);
		setClassId(class_id);
		this.date = date;
	}
	
	public void setValid(boolean status)
	{
		valid = status;
	}
	
	public boolean getValid()
	{
		return valid;
	}
	
	private void setStudent(String student_id)
	{
		this.student_id = student_id;
	}
	
	public String getStudent()
	{
		return student_id;
	}
	
	private void setClassId(String class_id)
	{
		this.class_id = class_id;
	}
	
	public String getClassId()
	{
		return class_id;
	}
	
}
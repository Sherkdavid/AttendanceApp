package modules;

import java.time.LocalDate;

public class Absence
{
	LocalDate date;
	String student_id, instance_id;
	boolean valid;
	
	public Absence(String student, String instance_id)
	{
		setValid(false);
		setStudent(student_id);
		setInstance(instance_id);
		date = LocalDate.now();
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
	
	private void setInstance(String instance_id)
	{
		this.instance_id = instance_id;
	}
	
	public String getinstanceId()
	{
		return instance_id;
	}
	
}
package model;

public class Faculty extends User{
	String department;
	
	public Faculty(String id, String name, String mail, String department) {
		super(id, name, mail);
		setDepartment(department);
	}
	
	public void setDepartment(String dept)
	{
		department = dept;
	}
	
	public String getDepartment()
	{
		return department;
	}


}

package users;

public class Lecturer extends User{	String department;
	
	public Lecturer(String id, String name, String mail, String department) {
		super(id, name, mail);
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

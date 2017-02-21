package users;

public class Student extends User {
	String course_id;
	int year;
	public Student(String id, String name, String course, String mail, int year)
	{
		super(id,name,mail);
		setYear(year);
		setCourse(course);
	}
	public Student(String id, String name, String mail)
	{
		super(id,name,mail);
	}
	private void setCourse(String course)
	{
		course_id = course;
	}
	public String getCourseId()
	{
		return course_id;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public void incrementYear()
	{
		year++;
	}
	public int getYear()
	{
		return year;
	}
	
	
	

}

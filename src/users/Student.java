package users;

public class Student extends User {
	String course_id;
	int year;
	public Student(String id, String name, String course, String mail)
	{
		super(id,name,mail);
		year = 1;
		setCourse(course);
	}
	private void setCourse(String course)
	{
		course_id = course;
	}
	public String getCourseId()
	{
		return course_id;
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

package model;
public class Class{
	String class_id, lecturer_id, module_id, title;
	
	public Class()
	{
	}
	public Class(String class_id, String module_id, String title,String lecturer_id)
	{
		this.class_id = class_id;
		this.module_id = module_id;
		this.lecturer_id = lecturer_id;
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	public void setLecturerId(String id)
	{
		lecturer_id = id;
	}
	public String getLecturerId()
	{
		return lecturer_id;
	}
	public String getClassId()
	{
		return class_id;
	}
		
	public String getModuleId()
	{
		return module_id;
	}

}
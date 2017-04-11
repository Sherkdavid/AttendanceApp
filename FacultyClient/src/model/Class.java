package model;

import java.io.Serializable;

public class Class implements Serializable{
	String class_id, lecturer_id, module_id;
	
	public Class()
	{
	}
	public Class(String class_id, String module_id, String lecturer_id)
	{
		this.class_id = class_id;
		this.module_id = module_id;
		this.lecturer_id = lecturer_id;
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
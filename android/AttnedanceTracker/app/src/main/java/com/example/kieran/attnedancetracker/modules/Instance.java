package com.example.kieran.attnedancetracker.modules;
public class Instance{
	String instance_id, lecturer_id, module_id;
	int lecture_count;
	public Instance(String instance_id, String module_id, int count)
	{
		setInstance(instance_id);
		setModule(module_id);
		setLectureCount(count);
	}
	
	public void setInstance(String instance)
	{
		instance_id = instance;
	}
	
	public String getInstance()
	{
		return instance_id;
	}
	
	public void setModule(String module)
	{
		module_id = module;
	}
	
	public String getModule()
	{
		return module_id;
	}
	
	private void setLectureCount(int count)
	{
		lecture_count = count;
	}
	
	public int getLectureCount()
	{
		return lecture_count;
	}
}
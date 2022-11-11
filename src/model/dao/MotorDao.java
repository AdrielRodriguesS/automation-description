package model.dao;

import java.util.List;

import model.entities.Motor;
import model.entities.Project;

public interface MotorDao {
	
	void insert(Motor obj, Project project);
	void update(Motor obj, Project project);
	void delete(String str, Project project);
	void deleteAll(Project project);
	List <Motor> findAll(Project project);
	Motor findById(String str, Project project);

}

package model.dao;

import java.util.List;

import model.entities.Pneumatic;
import model.entities.Project;

public interface PneumaticDao {
		
	void insert(Pneumatic obj, Project project);
	void update(Pneumatic obj, Project project);
	void delete(String str, Project project);
	void deleteAll(Project project);
	List <Pneumatic> findAll(Project project);
	Pneumatic findById(String str, Project project);
}

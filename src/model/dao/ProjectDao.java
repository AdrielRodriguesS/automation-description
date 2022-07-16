package model.dao;

import javafx.scene.control.TextField;
import model.entities.Project;

public interface ProjectDao {

	void updateProject(Project obj);
	void deleteProject(Project obj);
	Project loadProject(TextField txt);
	void insertProject(Project obj);
}

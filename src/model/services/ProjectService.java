package model.services;

import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.ProjectDao;
import model.entities.Project;

public class ProjectService {


	private ProjectDao dao = DaoFactory.criateProjectDao();
	
	public void updateProject(Project obj) {
		dao.updateProject(obj);
	}
	
	public void deleteProject(Project obj) {
		dao.deleteProject(obj);
	}
	
	public Project loadProject(TextField txt) {
		return dao.loadProject(txt);
	}
	
	public void InsertProject(Project obj) {
		dao.insertProject(obj);
	}

}

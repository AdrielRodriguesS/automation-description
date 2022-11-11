package model.services;


import java.util.List;

import model.dao.DaoFactory;
import model.dao.PneumaticDao;
import model.entities.Pneumatic;
import model.entities.Project;

public class PneumaticService {
	
	private PneumaticDao dao = DaoFactory.criatePneumaticDao();
	
	public void updatePneumatic(Pneumatic obj, Project project) {
		dao.update(obj, project);
	}
	
	public void deletePneumatic(String str, Project project) {
		dao.delete(str, project);
	}
	
	public void deleteAll(Project project) {
		dao.deleteAll(project);
	}
	
	public void insertPneumatic(Pneumatic obj, Project project) {
		dao.insert(obj, project);	
	}
	
	public List<Pneumatic> findAll(Project project){
		return dao.findAll(project);
	}
	
	public Pneumatic findById(String str, Project project) {
		return dao.findById(str, project);
	}
}

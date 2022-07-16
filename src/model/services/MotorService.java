package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MotorDao;
import model.entities.Motor;
import model.entities.Project;

public class MotorService {

	private MotorDao dao = DaoFactory.criateMotorDao();

	public void updateMotor(Motor obj, Project project) {
		dao.update(obj, project);
	}

	public void deleteMotor(String str, Project project) {
		dao.delete(str, project);
	}

	public void deleteAll(Project project) {
		dao.deleteAll(project);
	}

	public void insertMotor(Motor obj, Project project) {
		dao.insert(obj, project);
	}

	public List<Motor> findAll(Project project) {
		return dao.findAll(project);
	}

	public Motor findById(String str, Project project) {
		return dao.findById(str, project);
	}

}

package model.dao;

import db.DB;
import model.dao.inpl.MotorDaoJDBC;
import model.dao.inpl.PneumaticDaoJDBC;
import model.dao.inpl.ProjectDaoJDBC;

public class DaoFactory {
	

		public static ProjectDao criateProjectDao () {
			return new ProjectDaoJDBC(DB.getConnection());
		}

		public static PneumaticDao criatePneumaticDao() {
			return new PneumaticDaoJDBC(DB.getConnection());
		}
		
		public static MotorDao criateMotorDao() {
			return new MotorDaoJDBC(DB.getConnection());
		}

}

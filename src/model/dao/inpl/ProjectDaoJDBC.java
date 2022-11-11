package model.dao.inpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import javafx.scene.control.TextField;
import model.dao.ProjectDao;
import model.entities.Project;
import model.exceptions.ValidationException;

public class ProjectDaoJDBC implements ProjectDao {

	ValidationException exception = new ValidationException("Validation Exception");

	private Connection conn;

	public ProjectDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void updateProject(Project obj) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE project_data SET project_name = ?, project_client = ?, "
					+ "project_supply = ?, project_power = ?, main_description = ? " + "WHERE serial_number = ?");

			st.setString(1, obj.getProjectName());
			st.setString(2, obj.getClient());
			st.setString(3, obj.getSupply());
			st.setString(4, obj.getPower());
			st.setString(5, obj.getMainDescription());
			st.setString(6, obj.getSerialNumber());

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteProject(Project obj) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE FROM project_data WHERE serial_number = ?;");

			st.setString(1, obj.getSerialNumber());

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Project loadProject(TextField txt) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM project_data WHERE serial_number = ?;");

			st.setString(1, txt.getText());

			rs = st.executeQuery();

			Project project = new Project();
			if (rs.next()) {
				project = instantiateProject(rs);
			}

			return project;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public void insertProject(Project obj) {
		
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO project_data"
					+ "(project_name, project_client, serial_number, project_supply, project_power, main_description)"
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getProjectName());
			st.setString(2, obj.getClient());
			st.setString(3, obj.getSerialNumber());
			st.setString(4, obj.getSupply());
			st.setString(5, obj.getPower());
			st.setString(6, obj.getMainDescription());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();

				DB.closeResultSet(rs);
			}

		} catch (SQLException e) {
			throw new DbException("Unexpected Error! No rows affected");
		} finally {
			DB.closeStatement(st);
		}
	}

	private Project instantiateProject(ResultSet rs) throws SQLException {
		Project obj = new Project();
		obj.setProjectName(rs.getString("project_name"));
		obj.setSerialNumber(rs.getString("serial_number"));
		obj.setClient(rs.getString("project_client"));
		obj.setSupply(rs.getString("project_supply"));
		obj.setPower(rs.getString("project_power"));
		obj.setMainDescription(rs.getString("main_description"));
		obj.setId(rs.getInt("id"));
		return obj;
	}
}

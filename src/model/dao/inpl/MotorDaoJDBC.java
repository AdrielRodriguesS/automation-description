package model.dao.inpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MotorDao;
import model.entities.Motor;
import model.entities.Project;

public class MotorDaoJDBC implements MotorDao{
	
	private Connection conn;

	public MotorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Motor obj, Project project) {
		
		obj.setProjectId(project.getId());
		
		PreparedStatement st = null;
		
		try {				
			st = conn.prepareStatement(
					"INSERT INTO motors"
					+ "(tag, motor_type, motor_id, motor_description,"
					+ " operation_restriction, hmi_rescription, movement_rescription, project_id) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getTag());
			st.setString(2, obj.getMotorType());
			st.setString(3, obj.getMotorId());			
			st.setString(4, obj.getDescription());
			st.setString(5, obj.getOperationRestriction());
			st.setString(6, obj.getHmiDescription());
			st.setString(7, obj.getMovementDescription());
			st.setInt(8, obj.getProjectId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				
				DB.closeResultSet(rs);
			}
		}
		catch (SQLException e) {
				throw new DbException("Unexpected Error! No rows affected");			
		}				
		finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void update(Motor obj, Project project) {

		obj.setProjectId(project.getId());

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE motors "
					+ "SET tag = ?, motor_type = ?, motor_description = ?, "
					+ "operation_restriction = ?, hmi_description = ?, movement_description = ? "
					+ "WHERE project_id = ? AND motor_id = ?;");

			st.setString(1, obj.getTag());
			st.setString(2, obj.getMotorType());
			st.setString(3, obj.getDescription());
			st.setString(4, obj.getOperationRestriction());
			st.setString(5, obj.getHmiDescription());
			st.setString(6, obj.getMovementDescription());
			st.setInt(7, obj.getProjectId());
			st.setString(8, obj.getMotorId());

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void delete(String str, Project project) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE FROM motors WHERE project_id = ? AND motor_id = ?;");

			st.setInt(1, project.getId());
			st.setString(2, str);

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void deleteAll(Project project) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE FROM motors WHERE project_id = ?;");

			st.setInt(1, project.getId());

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Motor> findAll(Project project) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM motors WHERE project_id = ?");

			st.setInt(1, project.getId());

			rs = st.executeQuery();

			List<Motor> list = new ArrayList<>();

			while (rs.next()) {

				Motor obj = instantiateMotor(rs);
				list.add(obj);
			}

			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public Motor findById(String str, Project project) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM motors WHERE project_id = ? AND motor_id = ?;");
			
			st.setInt(1, project.getId());
			st.setString(2, str);
			
			rs = st.executeQuery();
			
			if (rs.next()){				
				Motor motor = instantiateMotor(rs);
				return motor;
			}		
			return null;
		
		}		
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

	private Motor instantiateMotor(ResultSet rs) throws SQLException {
		Motor obj = new Motor();
		obj.setTag(rs.getString("tag"));
		obj.setMotorType(rs.getString("motor_type"));
		obj.setMotorId(rs.getString("motor_id"));
		obj.setDescription(rs.getString("motor_description"));
		obj.setOperationRestriction(rs.getString("operation_restriction"));
		obj.setHmiDescription(rs.getString("hmi_description"));
		obj.setMovementDescription(rs.getString("movement_description"));
		obj.setProjectId(rs.getInt("project_id"));
		return obj;
	
	}

}

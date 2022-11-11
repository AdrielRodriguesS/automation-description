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
import model.dao.PneumaticDao;
import model.entities.Pneumatic;
import model.entities.Project;

public class PneumaticDaoJDBC implements PneumaticDao {

	private Connection conn;

	public PneumaticDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Pneumatic obj, Project project) {
		
		obj.setProjectId(project.getId());
		
		PreparedStatement st = null;
		
		try {				
			st = conn.prepareStatement(
					"INSERT INTO pneumatics"
					+ "(tag, pneumatic_type, pneumatic_id, pneumatic_description, associated_signal, fail_event,"
					+ " operation_restriction, hmi_description, movement_description, project_id) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getTag());
			st.setString(2, obj.getPneumaticType());
			st.setString(3, obj.getPneumaticId());			
			st.setString(4, obj.getDescription());
			st.setString(5, obj.getAssociatedSignal());
			st.setString(6, obj.getFailEvent());
			st.setString(7, obj.getOperationRestriction());
			st.setString(8, obj.getHmiDescription());
			st.setString(9, obj.getMovementDescription());
			st.setInt(10, obj.getProjectId());
			
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
	public void update(Pneumatic obj, Project project) {

		obj.setProjectId(project.getId());

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE pneumatics "
					+ "SET tag = ?, pneumatic_type = ?, pneumatic_description = ?, associated_signal = ?, fail_event = ?, "
					+ "operation_restriction = ?, hmi_description = ?, movement_description = ? "
					+ "WHERE project_id = ? AND pneumatic_id = ?;");

			st.setString(1, obj.getTag());
			st.setString(2, obj.getPneumaticType());
			st.setString(3, obj.getDescription());
			st.setString(4, obj.getAssociatedSignal());
			st.setString(5, obj.getFailEvent());
			st.setString(6, obj.getOperationRestriction());
			st.setString(7, obj.getHmiDescription());
			st.setString(8, obj.getMovementDescription());
			st.setInt(9, obj.getProjectId());
			st.setString(10, obj.getPneumaticId());

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
			st = conn.prepareStatement("DELETE FROM pneumatics WHERE project_id = ? AND tag = ?;");

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
			st = conn.prepareStatement("DELETE FROM pneumatics WHERE project_id = ?;");

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
	public List<Pneumatic> findAll(Project project) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM pneumatics WHERE project_id = ?");

			st.setInt(1, project.getId());

			rs = st.executeQuery();

			List<Pneumatic> list = new ArrayList<>();

			while (rs.next()) {

				Pneumatic obj = instantiatePneumatic(rs);
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
	public Pneumatic findById(String str, Project project) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM pneumatics WHERE project_id = ? AND pneumatic_id = ?;");
			
			st.setInt(1, project.getId());
			st.setString(2, str);
			
			rs = st.executeQuery();
			
			if (rs.next()){				
				Pneumatic pneumatic = instantiatePneumatic(rs);
				return pneumatic;
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
	

	private Pneumatic instantiatePneumatic(ResultSet rs) throws SQLException {
		Pneumatic obj = new Pneumatic();
		obj.setTag(rs.getString("tag"));
		obj.setPneumaticType(rs.getString("pneumatic_type"));
		obj.setPneumaticId(rs.getString("pneumatic_id"));
		obj.setDescription(rs.getString("pneumatic_description"));
		obj.setAssociatedSignal(rs.getString("associated_signal"));
		obj.setFailEvent(rs.getString("fail_event"));
		obj.setOperationRestriction(rs.getString("operation_restriction"));
		obj.setHmiDescription(rs.getString("hmi_description"));
		obj.setMovementDescription(rs.getString("movement_description"));
		obj.setProjectId(rs.getInt("project_id"));
		return obj;
	
	}
}

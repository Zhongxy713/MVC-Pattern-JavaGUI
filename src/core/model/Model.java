package core.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public abstract class Model {
	
	public Connect con = Connect.getConnection();
	
	protected String id;
	protected String table;
	
	public Model() {
		
	}
	public Model(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	protected abstract Model fillData(ResultSet rs);
	protected abstract Model insert();
	protected abstract Model update();
	
	public List<Model> getAllData() {
		
		List<Model> data = new Vector<>();
		try {
			String query = String.format("SELECT * FROM %s",this.table);
			
			ResultSet rs = con.executeQuery(query);
			while(rs.next()) {
				data.add(this.fillData(rs));
			}
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public Model findData(String id) {
		String query = String.format("SELECT * FROM %s WHERE GownId = '%s' LIMIT 1",this.table,id);
		ResultSet rs = con.executeQuery(query);
		try {
			while(rs.first()) {
				Model data = this.fillData(rs);
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteData() {
		try {
		String query = String.format("DELETE FROM %s WHERE GownId = ?", this.table);
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, this.id);
		ps.executeUpdate();
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	
	
	
}

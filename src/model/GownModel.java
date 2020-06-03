package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.model.Model;

public class GownModel extends Model {
	private String GownId;
	private String GownName;
	private String GownType;
	private int GownPrice;
	
	public String getGownId() {
		return GownId;
	}

	public void setGownId(String gownId) {
		GownId = gownId;
	}

	public String getGownName() {
		return GownName;
	}

	public void setGownName(String gownName) {
		GownName = gownName;
	}

	public String getGownType() {
		return GownType;
	}

	public void setGownType(String gownType) {
		GownType = gownType;
	}

	public int getGownPrice() {
		return GownPrice;
	}

	public void setGownPrice(int gownPrice) {
		GownPrice = gownPrice;
	}

	public GownModel() {
		this.table = "gowndata";
	}
	
	public GownModel(String gownId, String gownName, String gownType, int gownPrice) {
		super(gownId);
		this.table = "GownData";
		GownId = gownId;
		GownName = gownName;
		GownType = gownType;
		GownPrice = gownPrice;
	}

	@Override
	protected Model fillData(ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			GownModel g = new GownModel(rs.getString("GownId"),rs.getString("GownName"),rs.getString("GownType"),rs.getInt("GownPrice"));
			return g;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Model insert() {
		try {
			String query = String.format("INSERT INTO %s (GownId,GownName,GownType,GownPrice) VALUES(null,?,?,?)", this.table);
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, GownName);
			ps.setString(2, GownType);
			ps.setInt(3, GownPrice);
			ps.executeUpdate();
			return this;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Model update() {
		try {
			String query = String.format("UPDATE %S SET GownName = ? , GownType = ? , GownPrice = ? WHERE GownId = ?", this.table);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, GownName);
			ps.setString(2, GownType);
			ps.setInt(3, GownPrice);
			ps.setString(4, GownId);
			ps.executeUpdate();
			return this;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
	
	
}

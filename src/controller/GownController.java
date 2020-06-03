package controller;

import model.GownModel;
import view.ViewInterface;

import java.util.List;

import core.controller.*;
import core.model.Model;

public class GownController implements Controller {
	
	private GownModel gown;
	private static GownController gownc;
	
	public GownController() {
		this.gown = new GownModel();
	}
	
	
	public static GownController GetInstance() {
		if(gownc == null) {
			return new GownController();
		}
		else {
			return gownc;
		}
	}
	
	public GownModel insert(String gownId,String gownName,String gownType,int gownPrice) {
		GownModel g = new GownModel(gownId,gownName,gownType,gownPrice);
		g = (GownModel) g.insert();
		
		return g;
	}
	
	public GownModel update(String gownId,String gownName,String gownType,int gownPrice) {
		GownModel g = new GownModel(gownId,gownName,gownType,gownPrice);
		g = (GownModel) g.update();
		
		return g;
	}
	
	public boolean delete(String id) {
		GownModel g = (GownModel) gown.findData(id);
		
		return g.deleteData();
	}


	@Override
	public List<Model> getAllData() {
		// TODO Auto-generated method stub
		return gown.getAllData();
	}


	@Override
	public Model find(String id) {
		// TODO Auto-generated method stub
		return gown.findData(id);
	}


	@Override
	public ViewInterface view() {
		return new ViewInterface();
	}
	
}

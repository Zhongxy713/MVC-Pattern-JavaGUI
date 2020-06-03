package core.controller;

import java.util.List;

import core.model.Model;
import view.ViewInterface;

public interface Controller {
	public abstract List<Model> getAllData();
	public abstract Model find(String id);
	public abstract ViewInterface view();
	
}

package core.view;

import javax.swing.JFrame;

public abstract class View extends JFrame {
	protected String title;
	protected int width;
	protected int height;
	
	public abstract void initialize();
	public abstract void addComponent();
	public abstract void addListenerForm();
	
	public void showForm() {
		setVisible(true);
		setTitle(title);
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	};
	
	public void buildAll() {
		initialize();
		addComponent();
		addListenerForm();
		showForm();
	}
	

}

import controller.GownController;

public class Main {
	
	public Main() {
		GownController.GetInstance().view();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}

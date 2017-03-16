package observater;

import java.util.Observable;

public class Child extends Observable implements IBaby {

	private String name;
	
	public Child(String name) {
		this.name = name;
	}
	
	public void cry() {
		// System.out.println("小白哭了!");
		super.setChanged();
		super.notifyObservers(name+"哭了");
	}

	public void laugh(){
		super.setChanged();
		this.notifyObservers(name+"笑了");
	}
	

}

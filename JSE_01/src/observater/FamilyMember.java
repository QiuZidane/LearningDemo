package observater;

import java.util.Observable;
import java.util.Observer;

public class FamilyMember implements Observer{
	
	private String name;
	
	public FamilyMember(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(name + " 知道 " +arg.toString());
	}
	
	

}

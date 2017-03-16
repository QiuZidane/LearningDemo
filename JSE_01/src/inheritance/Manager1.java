package inheritance;
import abstractClasses.*;;

public class Manager1 extends Person {

	@Override
	public String getDescription() {
		return null;
	}
	
	public static void main(String...args) {
		
		Manager1 manager1 = new Manager1();
		manager1.getPersonName_protect();
		
	}
	
	

}

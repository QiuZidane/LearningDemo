package interfaces;

public class Test02 {

	public static void main(String[] args) {

		ICall icall1 = new DaughterCall();
		ICall icall2 = new SonCall();
		icall1.call();
		icall2.call();
		
	}

}

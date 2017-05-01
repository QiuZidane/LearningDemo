package observater;

public class OBTest {

	public static void main(String[] args) throws InterruptedException {

		Child jean = new Child("jean");
		Child jack = new Child("jack");
		FamilyMember even = new FamilyMember("even");
		FamilyMember zidane = new FamilyMember("zidane");
		jean.addObserver(even);
		jean.addObserver(zidane);
		jack.addObserver(even);
		jack.addObserver(zidane);
		jean.cry();
		jack.cry();
		Thread.sleep(2000);
		jean.laugh();
		jean.laugh();
		
	}

}

package inheritance;

public class ManagerTest{

	public static void main(String[] args)  {
		
		Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		
		Employee_ih[] staff = new Employee_ih[3];
		staff[0] = boss;
		staff[1] = new Employee_ih("Harry Hacker", 50001, 1989, 10, 1);
		staff[2] = new Employee_ih("Tommy Tester", 40000, 1990, 3, 15);
		
		for(Employee_ih e: staff){
			System.out.println("name = "+ e.getName() + ", salary = "+e.getSalary());
		}
		
		if (staff[1] instanceof Manager) {
			Manager boss1 = (Manager) staff[0];
		}
		
		try {
			Manager boss2 = (Manager) staff[1];
		} catch (ClassCastException e) {
			System.err.println("����ת��:"+e.getMessage());
		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}

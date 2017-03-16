package inheritance;

public class Manager extends Employee_ih {

	private double bonus;

	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		bonus = 0;		
	}

	public double getSalary() {

		double baseSalary = super.getSalary();
		return baseSalary + bonus;

	}

	public void setBonus(double b) {
		this.bonus = b;

	}
	
	public static void main(String args[]) {
		
		Employee_ih ih = new Employee_ih();
		ih.setName("123");
		System.out.println(ih.getName());
		
	}
	
}

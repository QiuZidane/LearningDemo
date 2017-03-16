package inheritance;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee_ih {

	private String name;
	private double salary;
	private Date hireDay;
	
	protected Employee_ih() {
		System.out.println("init!");
	}

	public Employee_ih(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	protected double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;

	}

}

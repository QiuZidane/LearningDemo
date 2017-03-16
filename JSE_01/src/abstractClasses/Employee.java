package abstractClasses;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Employee extends Person {

	private double salary;
	private Date hireDay;

	public Employee() {

		super("测试名字");
		salary = 0.000;
		GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
		hireDay = calendar.getTime();

	}

	public Employee(String n, double s, int year, int month, int day) {
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;

	}

	@Override
	public String getDescription() {

		return String.format("an employee with a salary of $%.2f", salary);
	}

	/**
	 * 覆盖equals
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Employee otherObj = (Employee) obj;

		if (otherObj.getName().equals(this.getName()) && otherObj.getSalary() == getSalary()
				&& otherObj.getHireDay().equals(getHireDay())) {
			return true;
		}

		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {

		
		return Objects.hash(1);
	}

}

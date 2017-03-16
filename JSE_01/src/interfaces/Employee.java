package interfaces;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Comparable<Employee>, Cloneable {

	private String name;
	private double salary;
	private Date hireDay;

	public Employee(String n, double s) {
		name = n;
		salary = s;
		hireDay = new Date();
		
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent;
		salary += raise;
	}

	/**
	 * Comparable<T>接口必须实现compareTo方法
	 */
	public int compareTo(Employee other) {
		return Double.compare(salary, other.salary);
	}

	@Override
	public String toString() {
		return "name=" + name + " salary=" + salary;
	}

	@Override
	/**
	 * 实现克隆方法
	 */
	protected Employee clone() throws CloneNotSupportedException {

		// 调用Object的克隆方法，克隆一个本对象--即Employee的实例
		Employee cloned = (Employee) super.clone();

		// 克隆具体域
		cloned.hireDay = (Date) hireDay.clone();

		return cloned;
	}

	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		System.out.println(newHireDay.getTime());
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void printHireDay() {
		System.out.println(hireDay);
		
	}

}

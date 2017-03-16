package interfaces;

import java.util.Arrays;

public class EmployeeSortTest {

	public static void main(String[] args) {

		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Employee0", 50000);
//		staff[0].setHireDay(2000, 10, 1);
		staff[1] = new Employee("Employee1", 60000);
		staff[2] = new Employee("Employee2", 40000);

		Arrays.sort(staff);

		for (Employee e : staff) {
			if (e instanceof Comparable) {
				System.out.println(e.toString());
				 e.setHireDay(2017, 10, 1);
				 e.printHireDay();
			}
		}

	}

}

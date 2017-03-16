package abstractClasses;

import java.util.Arrays;
import java.util.Objects;

public class PersonTest {

	public static void main(String[] args) {

		Person[] people = new Person[3];
		
		people[0] = new Employee("Zidane",50000,1982,10,1);
		people[1] = new Employee("Zidane",50000,1982,10,1);
		people[2] = new Student("Even","Society");
		
		for (Person p : people) {
			if (p instanceof Person) {
				System.out.println(p.getName() + ", " + p.getDescription());
			}
			
		}
		
		System.out.println(Arrays.equals( people, people));
		
		people[0].getPersonName_default();
		
		if (people[0].equals(people[1])) {
			System.out.println("我晕");
		}
		if (Objects.equals(people[0], people[1])) {
			System.out.println("乱码了");
		}
		
		System.out.println(Objects.hash(1));
		System.out.println(people[0].hashCode());
		System.out.println(people[1].hashCode());
		System.out.println(Objects.hash(people[1]));
		System.err.println(people[0].toString());
		System.out.println(people[0].getClass());
		
	}

}

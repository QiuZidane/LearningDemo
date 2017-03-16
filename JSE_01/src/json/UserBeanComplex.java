package json;

import java.awt.CardLayout;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class UserBeanComplex {

	@SerializedName("NAME")
	private String name;
	private short age;
	private transient String ignore; // transient声明的属性不会生成到json中
	private Date birthDay;
	private Set<String> major; // 职业
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int year, int month, int date) {
		this.birthDay = new Date(year, month, date);
	}
	
	public Set<String> getMajor(){
		return major;
	}
	
	public void setMajor(Set<String> major){
		this.major = major;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {

		return "name = " + name + "\nage = " + age + "\nignore = " + ignore + "\nbirthDay = "
				+ birthDay;

	}

}

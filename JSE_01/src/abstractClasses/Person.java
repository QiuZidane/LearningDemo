package abstractClasses;

public abstract class Person {

	public abstract String getDescription();
	
	void getPersonName_default(){
		System.out.println("呵呵");;
	};
	
	protected void getPersonName_protect(){
		System.out.println("呵呵呵1");
	};

	private String name;
	
	public Person() {
		this.name = "初始化名字";
	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}

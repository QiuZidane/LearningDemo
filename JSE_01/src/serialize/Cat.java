package serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Cat implements Serializable {

	private String name;
	
	public static int age = 10;

	public Cat() {
		this.name = "new cat";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {

		Cat cat = new Cat();
		
		Cat.age = 20;

		byte[] buff = null;

		// *******序列化*******

		// OutputStream os = new FileOutputStream("catDemo.out"); --> 可以序列化到一个文件内
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		

		try {

			ObjectOutputStream oos = new ObjectOutputStream(os);
			System.out.println("1>" + cat.getName());
			cat.setName("Hello Kitty");
			oos.writeObject(cat);

			buff = os.toByteArray(); // 需要将整个流都序列化了。。否则会流格式不对报错

			oos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// *******反序列化*******

		try {

			// InputStream is = new FileInputStream("catDemo.out");
			ByteArrayInputStream is = new ByteArrayInputStream(buff);
			ObjectInputStream ois = new ObjectInputStream(is);

			Cat cat2 = (Cat) ois.readObject();

			System.out.println("2>" + cat2.getName());

			is.close();
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

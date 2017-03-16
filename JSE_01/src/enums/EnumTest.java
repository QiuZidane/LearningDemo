package enums;

import java.awt.Color;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.util.HSSFColor.RED;

public class EnumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// System.out.println(MyColor.valueOf("RED"));

		for (MyColor myColor : MyColor.values()) //.values()返回当前枚举中定义的所有枚举项的集合
		{
			System.out.println(myColor);
			System.out.println(myColor.getSummy());
			System.out.println(myColor.getColor());
			System.out.println("==============================");
		}

		MyColor myColor = MyColor.RED;
		System.out.println("myColor=" + myColor);

		MyColor1 myColor1 = MyColor1.RED;
		System.out.println("myColor1=" + myColor1);
		System.out.println("myColor1's ordinal = " + myColor1.ordinal()); //方法ordinal返回枚举项在枚举对象中的序号
		System.out.println("myColor1.getDeclaringClass = " + myColor1.getDeclaringClass()); //取得当前枚举值所在类的完整名称
		
		// 枚举集合
		EnumSet<Role> es= EnumSet.of(Role.ROLEA, Role.ROLEB);
		EnumSet<Role> es1 = EnumSet.allOf(Role.class);
        for(Role role: es1)
        {
           System.out.println(role);
        }
        
        EnumMap<Role, String> emMap  = new EnumMap<>(Role.class);
        emMap.put(Role.SYSADMIN, "系统管理员");
        emMap.put(Role.ROLEA, "角色A");

        for(Map.Entry<Role, String> entry : emMap.entrySet()){
        	
        	System.out.println(entry.getKey()+" : "+entry.getValue());
        	
        };
        
        
        
        
	}

}

enum MyColor {

	RED("红色", Color.RED),
	BLUE("蓝色", Color.BLUE),
	GREEN("绿色", Color.GREEN);

	private String summy;
	private Color color;

	// 枚举类的构造器是提供给枚举对象中的枚举项构造时使用的，并不需要在枚举对象之外使用。所以只能是private
	private MyColor(String summy, Color color) {
		this.summy = summy;
		this.color = color;
	}

	public String getSummy() {
		return this.summy;
	}

	public Color getColor() {
		return this.color;
	}

}

enum MyColor1 {
	RED, BLUE, GREEN;
}

enum Role
{
    SYSADMIN,
    ROLEA,
    ROLEB,
    ROLEC;

}

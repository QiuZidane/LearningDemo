package encode;

import java.io.UnsupportedEncodingException;

import utils.CHexConver;

public class EnCodeTest {
	
	String content1 = "哈哈";
	String content2 = "����";
	
	public void code1(){
		
		
		
		System.out.println("content1 len ="+content1.length());
		try {
			System.out.println("content1.byte len  ISO-8859-1="+content1.getBytes("ISO-8859-1").length);
			System.out.println("content1.byte len  UTF-8="+content1.getBytes("UTF-8").length);
			System.out.println("content1.byte len  GBK="+content1.getBytes("GBK").length); // B9 FE B9 FE
			
			System.out.println("content2.byte len  ISO-8859-1="+content2.getBytes("ISO-8859-1").length);
			System.out.println("content2.byte len  UTF-8="+content2.getBytes("UTF-8").length);
			System.out.println("content2.byte len  GBK="+content2.getBytes("GBK").length);
			
			System.err.println(CHexConver.byte2HexStr(content1.getBytes("ISO-8859-1"),2) ); // 3F 3F
			System.err.println(CHexConver.byte2HexStr(content1.getBytes("UTF-8"),6) );
			System.err.println(CHexConver.byte2HexStr(content1.getBytes("GBK"),4) );
			
			System.err.println(CHexConver.byte2HexStr(content2.getBytes("ISO-8859-1"),4) ); // 3F 3F 3F 3F
			System.err.println(CHexConver.byte2HexStr(content2.getBytes("UTF-8"),12) ); // EF BF BD EF BF BD EF BF BD EF BF BD
			System.err.println(CHexConver.byte2HexStr(content2.getBytes("GBK"),4) ); // 3F 3F 3F 3F
			
//			3F 3F
//			E5 93 88 E5 93 88
//			B9 FE B9 FE
//			3F 3F 3F 3F
//			EF BF BD EF BF BD EF BF BD EF BF BD
//			3F 3F 3F 3F
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
//		System.out.println("content2 len ="+content2.length());
//		System.out.println("content2.byte len ="+content2.getBytes().length);
		
	}
	
	
	public void test2() {
		
		// 取到byte，但不知道码制
//		byte[] unknowCode = CHexConver.hexStr2Bytes("3F 3F 3F 3F"); //GBK
//		byte[] code1 = CHexConver.hexStr2Str("3F 3F 3F 3F").getBytes();
		
		
		
		// 转码为字符串
		try {
			byte[] unknowCode1 = content2.getBytes("UTF-8"); //GBK  --
			String str1  = new String(unknowCode1,"UTF-8"); 
			System.out.println("str1="+str1);
			
			
			String hexStr =CHexConver.str2HexStr(content2);
			byte[] unknowCode2 = CHexConver.hexStr2Bytes(hexStr);
			String str2  = new String(unknowCode2,"UTF-8"); // ����  说明本来是乱码则怎么都读不出来
			System.out.println("str2="+str2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * 
	 * @param args
	 */
	
	public void test3(){

		try {
			
		
		
		byte[] bytes = new byte[] { 50, 0, -1, 28, -24 };
		String sendString=new String(  bytes ,"ISO-8859-1");
		byte[] sendBytes= sendString .getBytes("UTF8");
		
		//然后再发送
		//接受时进行逆向转换
		String recString=new String( sendBytes ,"UTF-8");
		byte[] Mybytes=recString.getBytes("ISO-8859-1");
		System.out.println(Mybytes);
		//这时Mybytes中的数据将是[50, 0, -17, -65, -67, 28, -17, -65, -67]
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void test4() throws UnsupportedEncodingException{
		
		String content = "哈哈";
		String hexStr = CHexConver.str2HexStr(content); 
		
		byte[] GBKCode = CHexConver.hexStr2Bytes(hexStr); //GBK
		String code1 = CHexConver.hexStr2StrWithCode(hexStr,"GBK");
		String code2 = CHexConver.byte2HexStr(GBKCode, GBKCode.length);
		System.out.println(code1.getBytes().length);  // 正常的"哈哈"，
		System.out.println(CHexConver.hexStr2StrWithCode(code2,"UTF-8").getBytes().length); // 乱码的"哈哈"，导致长度不符
		
		
		String Str = "哈哈";
		// 转成十六进制
		String UTF8hexstr = CHexConver.str2HexStrWithCode(Str,"UTF8");
		// 转byte
		byte[] UTF8byte = Str.getBytes("UTF8");
		byte[] GBKbyte = Str.getBytes("GBK");
		
		String UTF8Strafter = CHexConver.hexStr2StrWithCode(UTF8hexstr,"UTF-8");
		String GBKStrafter = CHexConver.hexStr2StrWithCode(UTF8hexstr,"GBK");
		
		String msg1 = String.format("%s的hex=[%s], 转码[%s]后输出=%s, byte长度=%s", Str,UTF8hexstr,"UTF8",UTF8Strafter,UTF8Strafter.getBytes("UTF8").length);
		String msg2 = String.format("%s的hex=[%s], 转码[%s]后输出=%s, byte长度=%s", Str,UTF8hexstr,"GBK",GBKStrafter,GBKStrafter.getBytes("GBK").length);
		System.out.println(msg1);
		System.out.println(msg2);
		
	}
	
	public static void main(String[] args) {
		
		try {
			
		
		
//		new CodeTest().code1();
//		new CodeTest().test2();
//		new CodeTest().test3();
		new EnCodeTest().test4();
		
		
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

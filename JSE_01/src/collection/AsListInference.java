package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.MyUtil;

class A {}

class AA extends A {}

class AB extends A {}

public class AsListInference {

	public static void main(String[] args) {

		List<A> a1 = Arrays.asList(new AA(), new AB()); // 因为asList有两个参数，方法创建的目标类型会选择父类类型，因此编译通过
//		List<A> a2 = Arrays.asList(new AA()); // asList只有一个参数，因此会创建AA类型的List，导致编译报错
		List<A> a2_1 = Arrays.<A> asList(new AA()); // 可以在asList前加入一个"线索"，告诉编译器目标类型是什么，这种称为"显示类型参数说明"。

		List<A> a3 = new ArrayList<A>();
		Collections.addAll(a3, new AA()); // addAll方法从第一个参数了解到目标类型，因此编译通过

		MyUtil.traverseAndPrint(a1);

	}

}

//class Snow {}
//
//class Powder extends Snow {}
//
//class Light extends Powder {}
//
//class Heavy extends Powder {}
//
//class Crusty extends Snow {}
//
//class Slush extends Snow {}

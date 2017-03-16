package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import utils.MyUtil;

public class AddingGroups {

	
	public static void main(String[] args) {

		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)); // Arrays.asList(..)返回的是固定长度的list
		Integer[] moreInts = {6,7,8,9,10};
		collection.addAll(Arrays.asList(moreInts));
		collection.add(11);
		
		Collections.addAll(collection, 12,13,14,15);
		Collections.addAll(collection, moreInts);
		
		Iterator<Integer> iterator = collection.iterator(); // 这里有坑，如果在获取迭代器后又加入了元素，使用迭代器遍历集合时会报错
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			
		}
		
		MyUtil.traverseAndPrint(collection);
		
		List<Integer> list = Arrays.asList(1,2,3,4,5); 
		list.set(0, 111);
		//list.add(222); // Arrays.asList(..)返回的是固定长度的list,底层是固定长度的数组，因此Runtime会报错
//		MyUtil.traverseAndPrint(list);
		
		
		
	}

}

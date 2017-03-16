package groovy
import groovy.lang.DelegatingMetaClass

class MyMetaClass extends DelegatingMetaClass {

	MyMetaClass(Class thisClass){
		super(thisClass)
	}
	  
	Object invokeMethod(Object object, String methodName, Object[] arguments){
		"MyMetaClass: ${super.invokeMethod(object, methodName, arguments)}" // super指代被代理的类，这里是A
//		"$object,$methodName,$arguments"
	}
	
	static main(args) {
		
		def amc = new MyMetaClass(A)
		amc.initialize()
		def a = new A()
		a.metaClass = amc
		  
		println a.bark("haha","hehe")
		  
//		assert a.bark("haha") == 'MyMetaClass: A: invoked bark() haha'
		
//		Thread.start {
//			println a.bark()
//		}
		
//		println new A().bark()
//		
//		println new A().aaa(123,456)
	
	}

}

class A{
	def bark(String abc){'A: invoked bark() '+abc}
	def invokeMethod(String name, Object args){
		"A: missing $name(${args.join(', ')})"
	}
}
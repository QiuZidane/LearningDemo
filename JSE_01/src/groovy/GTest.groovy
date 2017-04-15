package groovy

import groovy.swing.impl.DefaultAction

class GTest implements GroovyInterceptable{

	def repeat(val){

		for(i in 1..5){
			println "this is ${i}:${val}"
		}
	}
	
	
	void hello1(){
		println "hello1"
	}
	
	Object getG(){
		"obob"
	}

	def hello(){
		'invoked hello() directly'
	}
	def invokeMethod(String name, Object args){	// 重写方法
		"invoked method $name(${args.join(', ')})"
	}
	
	def greeting = 'accessed greeting directly'
	Object getProperty(String property){   // 重写方法
		"read from property $property"
	}
  
	void setProperty(String property, Object newVlaue){ // 重写方法
		throw new Exception("wrote to property $property")
	}






	static main(args) {
		
		def test = new GTest();
		
		def var = """hello
						all
					"""

		assert test.hello() == 'invoked method hello()'
		assert test.foo('Mark',19) == 'invoked method foo(Mark, 19)'
		assert test.&hello() == 'invoked hello() directly'

		println test.&getG();
		
		assert test.greeting == "read from property greeting"
		try {
			test.greeting = "hello"
		} catch (Exception e) {
			println e.message //wrote to property greeting
		}
		
		println test.@greeting // 直接方法属性
		

	}
}

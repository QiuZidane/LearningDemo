package com.zidane.javademo

import org.apache.ivy.util.FileUtil

import com.sun.org.apache.bcel.internal.generic.NEW

class GroovyTest {
	
	def pp(arg){
		println "print $arg"
	}

	static main(args) {
		
		JavaTest.print()
		new GroovyTest().pp("hello")
		
	
	}

}

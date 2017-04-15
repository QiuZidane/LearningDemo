package interfaces

class GTest01 {

	static main(args) {
		
		ICall icall1 = new DaughterCall()
		def icall2 = new SonCall()
		icall1.call()
		icall2.call()

	}

}


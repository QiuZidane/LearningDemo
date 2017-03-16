package exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.UnknownException;

public class MultException {

	public MultException() {

	}

	public void getString() throws Throwable {

		/**
		 * 捕获多个异常
		 */

		try {
			
			/**
			 * 强烈建议这样使用，那么finally里面抛出来的异常也会被catch捕捉到
			 */
			try {
				
			} finally {
				
			}

		} catch (Exception e) {

			e.getClass().getName();
			Throwable se = new IOException("test");
			se.initCause(e); // 这种方法可以得到原始异常			
			throw se;

		} catch (Error e) {

		}

	}

}

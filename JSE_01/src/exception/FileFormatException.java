package exception;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;

import org.omg.CORBA.portable.UnknownException;

public class FileFormatException extends IOException {

	public FileFormatException() {
		System.out.println("this is FileFormatException");
	};

	public FileFormatException(String gripe) {
		super(gripe);
	}

}

package oop.ex7.main;
import java.io.File;
import java.io.IOException;

/**
 * Simplified Java Compiler.
 * Main method. More information in the README file.
 * 
 * @author aviadle, noammt
 */
public class Sjavac {
	private final static int INPUT_FILE = 0, ILLEGAL_CODE = 1, IO_ERROR = 2;

	public static void Hmain(String[] args) throws IOException {
		try {
			// get and convert user input:
			File _javaFile = new File(args[INPUT_FILE]);
			// parse java file:
			Parser.parseFile(_javaFile);
		} catch (IOException e) {
			System.out.println(IO_ERROR);
		} catch (SyntaxErrorException e) {
			System.out.println(ILLEGAL_CODE);
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(ILLEGAL_CODE);
		}
	}
}
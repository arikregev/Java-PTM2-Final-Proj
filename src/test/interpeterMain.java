package test;

import java.io.EOFException;
import java.util.List;

import interpreter.Interpreter;

public class interpeterMain {

	public static void main(String[] args) {
		Interpreter i = new Interpreter(new String[0]) {

			@Override
			public String getLine() throws EOFException {
				// TODO Auto-generated method stub
				return "OpenDataServer 5440 10  Connect 127.0.0.1  while true { alt=200 }";
			}
			
		};
		String s = "OpenDataServer 5440 10  Connect 127.0.0.1  while true { alt =200} bind /controls/break";
		//List<String> ss = i.lexer(s);
		//for(String t: ss)
		//	System.out.println(t);
	}

}

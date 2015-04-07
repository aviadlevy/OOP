package oop.ex7.main;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ref {
	
		public static void main(String[] args) throws ClassNotFoundException {
			Class c = Class.forName("oop.ex7.main.Variable.GeneralCheck");
			Method[] g = c.getDeclaredMethods();
			for (Method m : g) {
				System.out.println(m);
			}
			
		}


}




class Animal {

}

class Dog extends Animal {

}



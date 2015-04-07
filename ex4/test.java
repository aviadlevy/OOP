
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strarr = new String[23];
        strarr[0] = "hash";
        strarr[1] = "Hash";
        strarr[2] = "guitar";
        strarr[3] = "piano";
        strarr[4] = "violin";
        strarr[5] = "DAST";
        strarr[6] = "CLIDS";
        strarr[7] = "Infi";
        strarr[8] = "Hello";
        strarr[9] = "Bye";
        strarr[10] = "Test";
        strarr[11] = "Algebra";
        strarr[12] = "Boo";
        strarr[13] = "DAST";
        strarr[14] = "CLIDS";
        strarr[15] = "Shalom";
        strarr[16] = "Kushkush";
        strarr[17] = "hello";
        strarr[18] = "hi";
        strarr[19] = "naal";
        strarr[20] = "DAST";
        strarr[21] = "CLIDS";
        strarr[22] = "oink";
        OpenHashSet test = new OpenHashSet(strarr);
       
        System.out.println(test.
delete("Boo"));
        System.out.println(test.delete("Infi"));
        System.out.println(test.delete("Hello"));
        System.out.println(test.delete("hello"));
        System.out.println(test.delete("Hash"));
        System.out.println(test.add("Hash"));
        System.out.println(test.delete("Hash"));
        System.out.println(test.delete("Bye"));

	}

}

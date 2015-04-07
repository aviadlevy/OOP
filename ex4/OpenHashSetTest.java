import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nadav on 29/04/14.
 */
public class OpenHashSetTest {

    private int startIndex = 0;
    private boolean fullDebug = false;
    private int classToCreate = 0;


    @Test
    public void testAdd() throws Exception {
        OpenHashSet set = new OpenHashSet();
        set.add("ffff");
        org.junit.Assert.assertTrue(set.contains("ffff"));
        set.delete("ffff");
    }

    @Test
    public void executeTests() throws Exception {
        List<File> files = listf(System.getProperty("user.dir").concat("\\tests\\") );
        for (File file : files.subList(startIndex, files.size())) {
            System.out.println(file.getName());
            String[] testarr = Ex4Utils.file2array(file.getAbsolutePath());
            RunTest(testarr);
        }

    }

    public void RunTest(String[] arr){
        SimpleSet set = null;


        for(String line : arr){
            if (fullDebug)
                System.out.println(line);
            String[] words = line.split("\\s");
            if (words[0].startsWith("#")){
                System.out.println(line);
            }
            else if (line.equals("")){
                switch (classToCreate){
                    case 0:
                        set = new OpenHashSet();
                    case 1:
                        set = new ChainedHashSet();
                }
            }
            else if (words[0].equals("N:")){
                switch (classToCreate){
                    case 0:
                        set = new OpenHashSet( Float.parseFloat(words[1]), Float.parseFloat(words[2]));
                    case 1:
                        set = new ChainedHashSet( Float.parseFloat(words[1]), Float.parseFloat(words[2]));
                }

            }
            else if (words[0].equals("A:")){
                switch (classToCreate){
                    case 0:
                        set = new OpenHashSet(Arrays.copyOfRange(words, 1, words.length));
                    case 1:
                        set = new ChainedHashSet(Arrays.copyOfRange(words, 1, words.length));
                }
            }
            else if (words[0].equals("add")){
                boolean returnValue = set.add(words[1]);
                org.junit.Assert.assertTrue(Boolean.parseBoolean(words[2]) == returnValue);
            }
            else if (words[0].equals("delete")){
                boolean returnValue = set.delete(words[1]);
                org.junit.Assert.assertTrue(Boolean.parseBoolean(words[2]) == returnValue);
            }
            else if (words[0].equals("contains")){
                boolean returnValue = set.contains(words[1]);
                org.junit.Assert.assertTrue(Boolean.parseBoolean(words[2]) == returnValue);
            }
            else if (words[0].equals("size")){
                int returnValue = set.size();
                org.junit.Assert.assertTrue(Integer.parseInt(words[1]) == returnValue);
            }
            else if (words[0].equals("capacity")){
                int returnValue = ((SimpleHashSet) set).capacity();
                org.junit.Assert.assertTrue(Integer.parseInt(words[1]) == returnValue);
            }
            else {
                System.out.println("------------Bad Test -----------------");

                throw new IllegalArgumentException("Something is wrong with the tests");

            }

        }
        System.out.println("-----------------------Running Test-----------------");

        System.out.println("------------Completed Test -----------------");

    }

    public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));

        return resultList;
    }
}
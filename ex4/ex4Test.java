import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ex4Test {

	ChainedHashSet chainedHashSet;	
	OpenHashSet openHashSet;
	String newValue; 
	String newValue2;
	
	public void CheckContin(SimpleHashSet obj, String[] data ) {
		for (int i = 0; i < data.length ; i ++) {
			assertTrue(obj.contains(data[i]));
		}
	}
	
	
	@Before
	public void initializeTerm() {
		chainedHashSet = new ChainedHashSet();
		openHashSet = new OpenHashSet();
		newValue = "Hi";
		newValue2 = "Bye";
	}
	
	@Test
	public void test001() {		
		assertNotNull(chainedHashSet);
		assertNotNull(openHashSet);
	}
	
	@Test
	public void test002() {
		String[] data = new String[]{"1", "5", "9", "7"};
		for (int i = 0; i < data.length ; i ++) {
			chainedHashSet.add(data[i]);
			openHashSet.add(data[i]);
		}
		CheckContin(chainedHashSet, data);
		CheckContin(openHashSet, data);		
	}
	
	@Test
	public void test004() {
		String newValue = "hi";
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));				
	}
	
	@Test
	public void test005() {
		
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		
		assertFalse(chainedHashSet.add(newValue));
		assertFalse(openHashSet.add(newValue));
	}
	
	@Test
	public void test006() {
		
		assertFalse(chainedHashSet.contains(newValue));
		assertFalse(openHashSet.contains(newValue));		
	}
	
	@Test
	public void test007() {
		
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		assertTrue(chainedHashSet.contains(newValue));
		assertTrue(openHashSet.contains(newValue));		
	}
	
	@Test
	public void test008() {
		
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		assertTrue(chainedHashSet.delete(newValue));
		assertTrue(openHashSet.delete(newValue));		
	}
	
	@Test
	public void test009() {
				
		assertFalse(chainedHashSet.delete(newValue));
		assertFalse(openHashSet.delete(newValue));		
	}
	
	@Test
	public void test010() {
				
		assertTrue(chainedHashSet.size() == 0);
		assertTrue(openHashSet.size() == 0);		
	}
	
	@Test
	public void test011() {
		
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		assertTrue(chainedHashSet.size() == 1);
		assertTrue(openHashSet.size() == 1);		
	}
	@Test
	public void test012() {
		
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		assertTrue(chainedHashSet.delete(newValue));
		assertTrue(openHashSet.delete(newValue));
		assertTrue(chainedHashSet.size() == 0);
		assertTrue(openHashSet.size() == 0);		
	}
	
	@Test
	public void test013() {
		
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		assertFalse(chainedHashSet.delete(newValue2));
		assertFalse(openHashSet.delete(newValue2));
		assertTrue(chainedHashSet.size() == 1);
		assertTrue(openHashSet.size() == 1);		
	}
	
	@Test
	public void test014() {
		assertEquals(chainedHashSet.capacity(), 16);
		assertEquals(openHashSet.capacity(), 16);				
	}
	
	@Test
	public void test017() {
		chainedHashSet = new ChainedHashSet(0.7f, 0.1f);
		openHashSet = new OpenHashSet(0.7f, 0.1f);
		assertTrue(chainedHashSet.add(newValue));
		assertTrue(openHashSet.add(newValue));
		assertFalse(chainedHashSet.add(newValue));
		assertFalse(openHashSet.add(newValue));
		assertTrue(chainedHashSet.add(newValue2));
		assertTrue(openHashSet.add(newValue2));
		
		assertEquals(chainedHashSet.size(), 2);
		assertEquals(openHashSet.size(), 2);
		
		assertFalse(chainedHashSet.add(newValue));
		assertFalse(openHashSet.add(newValue));
		assertEquals(chainedHashSet.size(), 2);
		assertEquals(openHashSet.size(), 2);
		assertEquals(chainedHashSet.capacity(), 16);
		assertEquals(openHashSet.capacity(), 16);				
	}
	
	@Test
	public void test018() {
		
				
		chainedHashSet = new ChainedHashSet(0.7f, 0.1f);
		openHashSet = new OpenHashSet(0.7f, 0.1f);
		String[] data = new String[]{"Hi", "Hello", "Bye"};
		for (int i = 0; i < data.length ; i++) {
			assertTrue(chainedHashSet.add(data[i]));
			assertTrue(openHashSet.add(data[i]));
		}
		
		assertEquals(chainedHashSet.size(), 3);
		assertEquals(openHashSet.size(), 3);
		assertEquals(chainedHashSet.capacity(), 16);
		assertEquals(openHashSet.capacity(), 16);
		
		assertTrue(chainedHashSet.add("Ciao"));
		assertTrue(openHashSet.add("Ciao"));
		
		assertEquals(chainedHashSet.size(), 4);
		assertEquals(openHashSet.size(), 4);
		
		String[]  data1 = new String[]{"1", "2", "3", "4", "5", "6", "7"};
		for (int i = 0; i < data1.length ; i++) {
			
			assertTrue(chainedHashSet.add(data1[i]));
			assertTrue(openHashSet.add(data1[i]));
		}
		
		assertEquals(chainedHashSet.capacity(), 16);
		assertEquals(openHashSet.capacity(), 16);
		
		assertTrue(chainedHashSet.add("8"));
		assertTrue(openHashSet.add("8"));
		
		assertEquals(chainedHashSet.capacity(), 32);
		assertEquals(openHashSet.capacity(), 32);
		
		assertFalse(chainedHashSet.add("8"));
		assertFalse(openHashSet.add("8"));
		
		data = new String[]{ "9", "10", "11", "12", "13", "14"};
		for (int i = 0; i < data.length ; i ++) {
			assertTrue(chainedHashSet.add(data[i]));
			assertTrue(openHashSet.add(data[i]));
		}
		
		assertEquals(chainedHashSet.size(), 18);
		assertEquals(openHashSet.size(), 18);
		assertEquals(chainedHashSet.capacity(), 32);
		assertEquals(openHashSet.capacity(), 32);
	}
}

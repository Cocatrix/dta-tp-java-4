package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import parametervector.Vector;

public class TestVectorIterator {
	private Vector<Integer> v1;
	
	@Before
	public void doBefore() throws Exception {
		this.v1 = new Vector<Integer>(5);
	}
	
	// Testing the constructor		
	@Test
	public void constructingTest() throws Exception {
		assertEquals(5,v1.getLength());
		Vector<String> v0 = new Vector<String>(0);
		assertEquals(0,v0.getLength());
	}
	// Testing not creating a "-1"-length array
	@Test (expected = NegativeArraySizeException.class)
	public void constructingMinusOneTest() throws Exception {
		@SuppressWarnings("unused")
		Vector<Integer> vmin1 = new Vector<Integer>(-1);
	}
	// Testing ensureCapacity
	@Test
	public void ensuringCapacityTest() throws Exception {
		assertEquals(5,v1.getLength());
		v1.ensureCapacity(4);
		assertEquals(5,v1.getLength());
		v1.ensureCapacity(12);
		assertEquals(12,v1.getLength());
		v1.ensureCapacity(20);
		assertEquals(24,v1.getLength());
	}
	// Testing add
	@Test
	public void addingTest() throws Exception {
		v1.add(7);
		assertTrue(7 == v1.get(0));
		v1.add(-13);
		assertTrue(-13 == v1.get(1));
	}
	// Testing the iteration
	@Test
	public void iterateTest() throws Exception {
		v1.add(7);
		v1.add(8);
		v1.add(11);
		for (Integer i : v1) {
			System.out.println(i);
		}
	}
	// Testing size
	@Test
	public void gettingSizeTest() throws Exception {
		assertEquals(5,v1.getLength());
		assertEquals(0,v1.size());
		v1.add(4);
		v1.add(19);
		assertEquals(2,v1.size());
	}
	// Testing resize
	@Test
	public void resizingTest() throws Exception {
		v1.add(7);
		v1.resize(4);
		assertEquals(4,v1.size());
		v1.resize(2);
		assertEquals(2,v1.size());
		v1.resize(8);
		assertEquals(8,v1.size());
	}
	// Testing set
	@Test
	public void settingTest() throws Exception {
		v1.add(45);
		v1.add(14);
		v1.add(-4);
		assertEquals(14,(int)v1.get(1));
		v1.set(1, 21);
		assertEquals(21,(int)v1.get(1));
		v1.set(18, 14);

	}
	// Testing isEmpty
	@Test
	public void beingEmptyTest() throws Exception {
		assertTrue(v1.isEmpty());
		v1.add(1);
		assertFalse(v1.isEmpty());
		v1.resize(0);
		assertTrue(v1.isEmpty());
	}
	// Testing get
	@Test
	public void gettingTest() throws Exception {
		v1.add(45);
		v1.add(14);
		assertEquals(14,(int)v1.get(1));
	}
	@Test
	public void gettingNothingTest() throws Exception {
		assertEquals(null,v1.get(7));
	}
	
	// Testing addAll
	@Test
	public void addingAllTest() throws Exception {
		Vector<Integer> elements = new Vector<Integer>(4);
		elements.add(6);
		elements.add(8);
		elements.add(6);
		v1.addAll(elements);
		assertEquals(8,(int)v1.get(1));
	}
}

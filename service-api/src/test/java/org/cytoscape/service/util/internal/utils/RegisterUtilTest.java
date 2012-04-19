package org.cytoscape.service.util.internal.utils;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

public class RegisterUtilTest {

	private interface DummyInterface {
	}
	private class DummyClass implements DummyInterface{
		
	}
	
	private interface DummyInterface2 extends DummyInterface{
		
	}

	private class DummyClass2 implements DummyInterface2{
		
	}
	
	@Test
	public void TestGetAllInterfaces1 (){
		
		List<Class<?>> interfaces = RegisterUtil.getAllInterfaces(DummyClass.class);
		assertEquals(DummyInterface.class, interfaces.get(0));

	}
	@Test
	public void TestGetAllInterfaces2 (){
		
		List<Class<?>> interfaces = RegisterUtil.getAllInterfaces(DummyClass2.class);
		assertEquals(DummyInterface2.class, interfaces.get(0));
		assertEquals(DummyInterface.class, interfaces.get(1));

	}
}

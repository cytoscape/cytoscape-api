package org.cytoscape.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NamespaceParseTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{"MyNamespace::MyName", "MyNamespace", "MyName"},
			{"  My Namespace  ::  My Name  ", "  My Namespace  ", "  My Name  "},
			{"MyNamespace::MyName::AndMore", "MyNamespace", "MyName::AndMore"},
			{"JustName", null, "JustName"},
			{"::JustName", "", "JustName"},
			{"  ::JustName", "  ", "JustName"},
			{"JustNamespace::", "JustNamespace", ""},
			{"::", "", ""}
		});
	}
	
	@Parameter(0) public String fullyQualified;
	@Parameter(1) public String namespace;
	@Parameter(2) public String name;
	
	@Test
	public void testSplit() {
		String[] parts = CyColumn.splitColumnName(fullyQualified);
		assertEquals(namespace, parts[0]);
		assertEquals(name, parts[1]);
	}
	
	@Test
	public void testJoin() {
		String full = CyColumn.joinColumnName(namespace, name);
		assertEquals(fullyQualified, full);
	}
}

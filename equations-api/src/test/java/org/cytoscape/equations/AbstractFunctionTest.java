package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class AbstractFunctionTest {
	private final Function sf = new SimpleFunction();

	@Test
	public void testGetUsageDescription() {
		final String usage = sf.getUsageDescription();
		assertTrue("The usage description was either null or the empty string.", usage != null && !usage.isEmpty());
	}

	@Test
	public void testValidateArgTypes() {
		final Class<?>[] argTypes = { Double.class, Double.class };
		assertEquals("The validateArgTypes() method is buggy.", sf.getReturnType(), sf.validateArgTypes(argTypes));
	}

	@Test
	public void testValidateArgTypesWithBadArgs() {
		final Class<?>[] argTypes = { List.class, List.class };
		assertNull("The validateArgTypes() method is buggy.", sf.validateArgTypes(argTypes));
	}

	@Test
	public void testValidateArgTypesWithTooManyArgs() {
		final Class<?>[] argTypes = { Double.class, Double.class, List.class };
		assertNull("The validateArgTypes() method is buggy.", sf.validateArgTypes(argTypes));
	}

	@Test
	public void testGetPossibleArgTypes() {
		final Class<?>[] empytyArgList = { };
		final Class<?>[] singleArgArgList = { Double.class };

		final List<Class<?>> nextArgs1 = sf.getPossibleArgTypes(empytyArgList);
		assertTrue("Empty arg list resulted in incorrect behaviour of getPossibleArgTypes().",
			   nextArgs1.contains(Double.class));

		final List<Class<?>> nextArgs2 = sf.getPossibleArgTypes(singleArgArgList);
		assertTrue("Single arg arg list resulted in incorrect behaviour of getPossibleArgTypes().",
			   nextArgs2.contains(Double.class) && nextArgs2.contains(null));
	}

	@Test(expected=IllegalStateException.class)
	public void testGetPossibleArgTypesWithTooManyArgs() {
		final Class<?>[] argList = { Double.class, Double.class, Double.class };
		sf.getPossibleArgTypes(argList);
	}

	@Test(expected=IllegalStateException.class)
	public void testGetPossibleArgTypesWithABadArg() {
		final Class<?>[] singleArgArgList = { List.class };
		sf.getPossibleArgTypes(singleArgArgList);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFunctionWithBadArgDescriptors() {
		final Function f = new BadFunction();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFunctionWithBadArgNames() {
		final Function f = new BadFunction2();
	}
}


class BadFunction extends AbstractFunction {
	BadFunction() {
		super(new ArgDescriptor[] {
                                new ArgDescriptor(ArgType.OPT_FLOAT, "argument", "A positive number."),
                                new ArgDescriptor(ArgType.FLOAT, "base", "A positive number.")
                        });
	}

	public String getName() { return "SIMPLE"; }
	public String getFunctionSummary() { return "blah blah..."; }
	public Class<?> getReturnType() { return Integer.class; }
	public Object evaluateFunction(final Object[] args) throws FunctionError { return null; }
}


class BadFunction2 extends AbstractFunction {
	BadFunction2() {
		super(new ArgDescriptor[] { // We have two args with the *same* name!
                                new ArgDescriptor(ArgType.FLOAT, "argument", "A positive number."),
                                new ArgDescriptor(ArgType.FLOAT, "argument", "A positive number.")
                        });
	}

	public String getName() { return "SIMPLE"; }
	public String getFunctionSummary() { return "blah blah..."; }
	public Class<?> getReturnType() { return Integer.class; }
	public Object evaluateFunction(final Object[] args) throws FunctionError { return null; }
}

package org.cytoscape.equations;


class SimpleFunction extends AbstractFunction {
	SimpleFunction() {
		super(new ArgDescriptor[] {
                                new ArgDescriptor(ArgType.FLOAT, "argument", "A positive number."),
                                new ArgDescriptor(ArgType.OPT_FLOAT, "base", "A positive number.")
                        });
	}

	@Override
	public String getName() { return "SIMPLE"; }

	@Override
	public String getFunctionSummary() { return "blah blah..."; }

	@Override
	public Class getReturnType() { return Integer.class; }

	@Override
	public Object evaluateFunction(final Object[] args) throws FunctionError { return null; }
}

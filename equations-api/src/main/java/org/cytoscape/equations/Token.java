package org.cytoscape.equations;


public final class Token {
	
	public enum Type {
		STRING_CONSTANT,
		FLOAT_CONSTANT,
		BOOLEAN_CONSTANT,
		IDENTIFIER,
		OPEN_BRACE,
		CLOSE_BRACE,
		OPEN_PAREN,
		CLOSE_PAREN,
		COLON,
		CARET,
		PLUS,
		MINUS,
		DIV,
		MUL,
		EQUAL,
		NOT_EQUAL,
		GREATER_THAN,
		LESS_THAN,
		GREATER_OR_EQUAL,
		LESS_OR_EQUAL,
		DOLLAR,
		COMMA,
		AMPERSAND,
		EOS,
		ERROR;
	}
	
	private final Type type;
	private final int start;
	private final int end;
	
	
	public Token(Type type, int start, int end) {
		this.type = type;
		this.start = start;
		this.end = end;
	}


	public Type getType() {
		return type;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
}



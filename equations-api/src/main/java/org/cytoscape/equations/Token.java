package org.cytoscape.equations;

import java.util.Objects;

/**
 * Represents the interpretation of a substring of an equation string.
 * Provides the type of each token and its start/end location within the equation.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule equations-api
 * 
 * @since 3.9
 */
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
	
	public int getLength() {
		return end - start;
	}


	@Override
	public int hashCode() {
		return Objects.hash(end, start, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Token))
			return false;
		Token other = (Token) obj;
		return end == other.end && start == other.start && type == other.type;
	}

	@Override
	public String toString() {
		return "Token[" + type + "," + start + "," + end + "]";
	}
	
}


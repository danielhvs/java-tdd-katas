package br.com.danielhabib.stringcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DelimiterParserTest {
	private DelimiterParser parser;

	@Before
	public void setup() {
		parser = new DelimiterParser();
	}

	@Test
	public void getDelimiter_ExpressionWithNoDelimiter_ReturnsDefaultDelimiter() throws Exception {

		String delimiter = parser.getDelimiter("1,2");

		assertEquals(",\n", delimiter);
	}

	@Test
	public void getDelimiter_ExpressionWithDelimiter_ReturnsTheDelimitersDefinedAndTheDefaults() throws Exception {

		String delimiter = parser.getDelimiter("//;1;2,3");

		assertEquals(",\n;", delimiter);
	}

	@Test
	public void getExpression_ExpressionWithDelimiter_ReturnsOnlyTheExpression() throws Exception {

		String expression = parser.getExpression("//;1;2,3");

		assertEquals("1;2,3", expression);
	}

	@Test
	public void getExpression_ExpressionWithNoDelimiter_ReturnsIt() throws Exception {

		String expression = parser.getExpression("1,2");

		assertEquals("1,2", expression);
	}
}

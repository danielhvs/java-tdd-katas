package br.com.danielhabib.stringcalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.Configure;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
@Configure(separator = "-")
public class StringCalculatorTest {
	private StringCalculator calculator;

	@Before
	public void setup() {
		calculator = new StringCalculator(new DelimiterParser());
	}

	@TestWith({ "1 - 1", "2 - 2" })
	public void add_singleNumber_ReturnsIt(int expected, String expression) throws Exception {

		int actual = calculator.add(expression);

		assertEquals(expected, actual);
	}

	@Test
	public void add_emptyInput_ReturnsZero() throws Exception {

		int actual = calculator.add("");

		assertEquals(0, actual);
	}

	@TestWith({ "2 - 1,1", "3 - 1,2" })
	public void add_TwoNumbers_ReturnsTheSum(int expected, String expression) throws Exception {

		int actual = calculator.add(expression);

		assertEquals(expected, actual);
	}

	@Test
	public void add_ManyNumbers_ReturnsTheSum() throws Exception {

		int actual = calculator.add("1,2,3,4,5,6");

		assertEquals(21, actual);
	}

	@Test
	public void add_DifferentDelimiter_ReturnsTheSum() throws Exception {
		int actual = calculator.add("1\n2");

		assertEquals(3, actual);
	}

	@Test
	public void add_DelimiterCombination_AcceptsIt() throws Exception {
		int actual = calculator.add("1\n2,3");

		assertEquals(6, actual);
	}

	@Test
	public void add_ConfigurableDelimiter_UsesItAndStillConsiderDefaultDelimiters() throws Exception {
		int actual = calculator.add("//;\n1;2");

		assertEquals(3, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void add_NegativeNumber_ThrowsException() throws Exception {
		calculator.add("-1");
	}

	@Test
	public void add_NegativeNumbers_ThrowsExceptionWithMessageContainingAllNegativeNumbers() throws Exception {
		try {
			calculator.add("-1,-2,-3");
			fail("Should have thrown an exception.");
		}catch(IllegalArgumentException e) {
			assertTrue("Actual msg: " + e.getMessage(), e.getMessage().contains("-1,-2,-3"));
		}
	}

	@Test
	public void add_NumbersBigThanMaximum_AreDisconsidered() throws Exception {
		int actual = calculator.add("1001,2");

		assertEquals(2, actual);
	}


}

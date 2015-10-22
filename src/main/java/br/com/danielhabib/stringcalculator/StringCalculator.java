package br.com.danielhabib.stringcalculator;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {

	private final DelimiterParser parser;

	public StringCalculator(DelimiterParser parser) {
		this.parser = parser;
	}

	public int add(String rawExpression) {
		if (StringUtils.isEmpty(rawExpression)) {
			return 0;
		} else {
			String delimiter = "[" + parser.getDelimiter(rawExpression) + "]";
			String expression = parser.getExpression(rawExpression);
			return sumNumbers(expression, delimiter);
		}
	}

	private int sumNumbers(String expression, String delimiter) {
		String[] tokens = expression.split(delimiter);
		int result = 0;
		String negativeNumbers = "";
		for (String token : tokens) {
			int number = toNumber(token);
			if (number < 0) {
				negativeNumbers += String.valueOf(number) + ",";
			}
			result += number;
		}
		throwExceptionIf(negativeNumbers);
		return result;
	}

	private void throwExceptionIf(String negativeNumbers) {
		if (StringUtils.isNotEmpty(negativeNumbers)) {
			throw new IllegalArgumentException("Negatives not allowed:" + negativeNumbers);
		}
	}

	private int toNumber(String string) {
		Integer number = Integer.valueOf(string);
		return number > 1000 ? 0 : number;
	}

}

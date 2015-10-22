package br.com.danielhabib.stringcalculator;

public class DelimiterParser {
	private static final String DEFAULT_DELIMITERS = ",\n";

	public String getDelimiter(String rawExpression) {
		String delimiters = DEFAULT_DELIMITERS;
		if (rawExpression.startsWith("//")) {
			for (int i = 2; i < rawExpression.length(); i++) {
				char token = rawExpression.charAt(i);
				if (Character.isDigit(token)) {
					break;
				}
				delimiters += token;
			}
		}

		return delimiters;
	}

	public String getExpression(String rawExpression) {
		int indexOfExpression = 0;
		if (rawExpression.startsWith("//")) {
			for (int i = 2; i < rawExpression.length(); i++) {
				char token = rawExpression.charAt(i);
				if (Character.isDigit(token)) {
					indexOfExpression = i;
					break;
				}
			}
		}

		return rawExpression.substring(indexOfExpression);
	}
}

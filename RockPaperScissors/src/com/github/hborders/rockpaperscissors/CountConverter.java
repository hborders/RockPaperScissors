package com.github.hborders.rockpaperscissors;

public class CountConverter {
	public int convertCount(String rawCount)
			throws CountConverter.InvalidGameCountException {
		try {
			int count = new Integer(rawCount);
			if (count < 1) {
				throw new CountConverter.InvalidGameCountException();
			}

			return count;
		} catch (NumberFormatException numberFormatException) {
			throw new CountConverter.InvalidGameCountException();
		}
	}

	public static class InvalidGameCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
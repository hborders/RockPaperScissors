package com.github.hborders.rockpaperscissors;

public class CountConverter {
	public int convertCount(String rawCount)
			throws CountConverter.InvalidCountException {
		try {
			int count = new Integer(rawCount);
			if (count < 1) {
				throw new CountConverter.InvalidCountException();
			}

			return count;
		} catch (NumberFormatException numberFormatException) {
			throw new CountConverter.InvalidCountException();
		}
	}

	public static class InvalidCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountConverterTest {

	private CountConverter testObject;

	@Before
	public void setup() {
		testObject = new CountConverter();
	}

	@Test(expected = CountConverter.InvalidCountException.class)
	public void convertCount_throws_InvalidGameCountException_when_count_is_not_a_number()
			throws Exception {
		testObject.convertCount("foo");
	}

	@Test(expected = CountConverter.InvalidCountException.class)
	public void convertCount_throws_InvalidGameCountException_when_count_is_less_than_1()
			throws Exception {
		testObject.convertCount("0");
	}

	@Test
	public void convertCount_returns_int_corresponding_to_rawCount_when_count_is_greater_than_zero()
			throws Exception {
		int count = testObject.convertCount("1");

		assertEquals(1, count);
	}
}

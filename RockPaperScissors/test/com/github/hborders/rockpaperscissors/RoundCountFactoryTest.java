package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class RoundCountFactoryTest {

	private RoundCountFactory testObject;

	@Before
	public void setup() {
		testObject = new RoundCountFactory();
	}

	@Test(expected = InvalidRoundCountException.class)
	public void createRoundCount_throws_InvalidRoundCountException_when_input_is_not_a_number()
			throws Exception {
		testObject.createRoundCount("foo");
	}

	@Test(expected = InvalidRoundCountException.class)
	public void createRoundCount_throws_InvalidRoundCountException_when_input_is_less_than_1()
			throws Exception {
		testObject.createRoundCount("0");
	}

	@Test
	public void createRoundCount_returns_RoundCount_when_input_is_greater_than_zero()
			throws Exception {
		RoundCount roundCount = testObject.createRoundCount("1");

		assertEquals(1, roundCount.getRoundCount());
	}
}

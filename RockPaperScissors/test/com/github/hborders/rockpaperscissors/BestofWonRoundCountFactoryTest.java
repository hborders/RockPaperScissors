package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofWonRoundCountFactoryTest {

	private BestofWonRoundCountFactory testObject;

	@Before
	public void setup() {
		testObject = new BestofWonRoundCountFactory();
	}

	@Test(expected = InvalidWonRoundCountException.class)
	public void createWonRoundCount_throws_InvalidWonRoundCountException_when_input_is_not_a_number()
			throws Exception {
		testObject.createWonRoundCount("foo");
	}

	@Test(expected = InvalidWonRoundCountException.class)
	public void createWonRoundCount_throws_InvalidWonRoundCountException_when_input_is_less_than_1()
			throws Exception {
		testObject.createWonRoundCount("0");
	}

	@Test(expected = InvalidWonRoundCountException.class)
	public void createWonRoundCount_throws_InvalidWonRoundCountException_when_input_is_even()
			throws Exception {
		testObject.createWonRoundCount("2");
	}

	@Test
	public void createWonRoundCount_returns_BestofCount_when_input_is_odd_number()
			throws Exception {
		WonRoundCount wonRoundCount = testObject.createWonRoundCount("1");

		assertNotNull(wonRoundCount);
	}
}

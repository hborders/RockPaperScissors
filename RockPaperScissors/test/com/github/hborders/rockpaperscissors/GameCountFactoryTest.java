package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class GameCountFactoryTest {

	private GameCountFactory testObject;

	@Before
	public void setup() {
		testObject = new GameCountFactory();
	}

	@Test(expected = InvalidGameCountException.class)
	public void createGameCount_throws_InvalidGameCountException_when_input_is_not_a_number()
			throws Exception {
		testObject.createGameCount("foo");
	}

	@Test(expected = InvalidGameCountException.class)
	public void createGameCount_throws_InvalidGameCountException_when_input_is_less_than_1()
			throws Exception {
		testObject.createGameCount("0");
	}

	@Test
	public void createGameCount_returns_GameCount_when_input_is_greater_than_zero()
			throws Exception {
		GameCount gameCount = testObject.createGameCount("1");

		assertNotNull(gameCount);
	}
}

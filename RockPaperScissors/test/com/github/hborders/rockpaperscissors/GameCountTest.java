package com.github.hborders.rockpaperscissors;

import org.junit.Test;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class GameCountTest {
	@Test(expected = InvalidGameCountException.class)
	public void constructor_throws_InvalidGameCountException_when_gameCount_is_not_a_number()
			throws Exception {
		new GameCount("foo");
	}

	@Test(expected = InvalidGameCountException.class)
	public void constructor_throws_InvalidGameCountException_when_gameCount_is_less_than_1()
			throws Exception {
		new GameCount("0");
	}
}

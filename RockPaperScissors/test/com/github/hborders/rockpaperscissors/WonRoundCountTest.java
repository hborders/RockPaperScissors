package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Test;

public class WonRoundCountTest {
	@Test
	public void matches_returns_true_when_counts_are_equal() {
		assertTrue(new WonRoundCount(1).matches(new WonRoundCount(1)));
	}

	@Test
	public void matches_returns_false_when_counts_are_not_equal() {
		assertFalse(new WonRoundCount(1).matches(new WonRoundCount(2)));
	}

	@Test
	public void incremented_WonRoundCount_matches_unincremented_WonRoundCount_with_initial_count_greater_by_1() {
		WonRoundCount unincrementedWonRoundCount = new WonRoundCount(1);
		WonRoundCount incrementedWonRoundCount = new WonRoundCount(0);

		incrementedWonRoundCount.increment();

		assertTrue(unincrementedWonRoundCount.matches(incrementedWonRoundCount));
	}
}

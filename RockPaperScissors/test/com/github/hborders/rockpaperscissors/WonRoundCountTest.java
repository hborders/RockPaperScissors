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
	public void incremented_WonRoundCount_equals_unincremented_WonRoundCount_with_initial_count_greater_by_1() {
		WonRoundCount incrementedWonRoundCount = new WonRoundCount(0);

		incrementedWonRoundCount.increment();

		assertEquals(new WonRoundCount(1), incrementedWonRoundCount);
	}

	@Test
	public void matching_WonRoundCounts_are_equal() throws Exception {
		assertEquals(new WonRoundCount(1), new WonRoundCount(1));
		assertEquals(new WonRoundCount(1).hashCode(), new WonRoundCount(1)
				.hashCode());
	}

	@Test
	public void compareTo_returns_negative_with_greater_WonRoundCount()
			throws Exception {
		assertTrue(new WonRoundCount(2).compareTo(new WonRoundCount(5)) < 0);
	}

	@Test
	public void compareTo_returns_zero_with_equal_WonRoundCount()
			throws Exception {
		assertEquals(0, new WonRoundCount(2).compareTo(new WonRoundCount(2)));
	}

	@Test
	public void compareTo_returns_positive_with_lesser_WonRoundCount()
			throws Exception {
		assertTrue(0 < new WonRoundCount(5).compareTo(new WonRoundCount(2)));
	}
}

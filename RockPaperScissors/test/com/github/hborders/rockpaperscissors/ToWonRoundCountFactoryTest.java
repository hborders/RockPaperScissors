package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToWonRoundCountFactoryTest {
	private RoundCount mockToRoundCount;

	private ToWonRoundCountFactory testObject;

	@Before
	public void setup() {
		mockToRoundCount = mock(RoundCount.class);

		testObject = new ToWonRoundCountFactory();
	}

	@Test
	public void createWonRoundCount_returns_WonRoundCount_with_round_count() {
		when(mockToRoundCount.getRoundCount()).thenReturn(4);

		WonRoundCount wonRoundCount = testObject
				.createWonRoundCount(mockToRoundCount);

		assertTrue(wonRoundCount.matches(new WonRoundCount(4)));
	}
}

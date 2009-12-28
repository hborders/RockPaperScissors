package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofWonRoundCountFactoryTest {

	private BestofWonRoundCountFactory testObject;

	private RoundCount mockRoundCount;

	@Before
	public void setup() {
		testObject = new BestofWonRoundCountFactory();

		mockRoundCount = mock(RoundCount.class);
	}

	@Test(expected = InvalidWonRoundCountException.class)
	public void createWinningWonRoundCount_throws_InvalidWonRoundCountException_when_RoundCount_is_even()
			throws Exception {
		when(mockRoundCount.getRawRoundCount()).thenReturn(2);

		testObject.createWinningWonRoundCount(mockRoundCount);
	}

	@Test
	public void createWinningWonRoundCount_returns_WonRoundCount_divided_by_2_plus_1_when_RoundCount_is_odd_number()
			throws Exception {
		when(mockRoundCount.getRawRoundCount()).thenReturn(5);

		WonRoundCount wonRoundCount = testObject
				.createWinningWonRoundCount(mockRoundCount);

		assertThat(wonRoundCount, new BaseMatcher<WonRoundCount>() {
			private final WonRoundCount expectedWonRoundCount = new WonRoundCount(
					3);

			@Override
			public void describeTo(Description description) {
				description.appendText("Expected to match: "
						+ expectedWonRoundCount);
			}

			@Override
			public boolean matches(Object item) {
				return ((WonRoundCount) item).matches(expectedWonRoundCount);
			}
		});
		;
	}
}

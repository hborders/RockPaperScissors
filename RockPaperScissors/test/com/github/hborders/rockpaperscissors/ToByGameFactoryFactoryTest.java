package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToByGameFactoryFactoryTest {
	private RoundCountFactory mockRoundCountFactory;
	private ToByWonRoundCountFactory mockToByWonRoundCountFactory;
	private AttemptReader mockAttemptReader;

	private ToByGameFactoryFactory testObject;

	private RoundCount mockToRoundCount;

	@Before
	public void setup() {
		mockRoundCountFactory = mock(RoundCountFactory.class);
		mockToByWonRoundCountFactory = mock(ToByWonRoundCountFactory.class);
		mockAttemptReader = mock(AttemptReader.class);

		testObject = new ToByGameFactoryFactory(mockRoundCountFactory,
				mockToByWonRoundCountFactory, mockAttemptReader);

		mockToRoundCount = mock(RoundCount.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGameFactory(mockToRoundCount, new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGameFactory(mockToRoundCount, new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_RoundCountFactory_throws_InvalidRoundCountException()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenThrow(
				new InvalidRoundCountException());

		testObject.createGameFactory(mockToRoundCount, new String[] { "", "",
				"-by", "foo" });
	}

	@Test
	public void createGameFactory_returns_GameFactory_from_GameFactory_Provider_when_RoundCount_Provider_returns_RoundCount()
			throws Exception {
		RoundCount mockByRoundCount = mock(RoundCount.class);
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockByRoundCount);

		WonRoundCount mockWinningWonRoundCount = mock(WonRoundCount.class);
		when(
				mockToByWonRoundCountFactory.createWinningWonRoundCount(
						mockToRoundCount, mockByRoundCount)).thenReturn(
				mockWinningWonRoundCount);

		WonRoundCount mockExtendingWonRoundCount = mock(WonRoundCount.class);
		when(
				mockToByWonRoundCountFactory.createExtendingWonRoundCount(
						mockToRoundCount, mockByRoundCount)).thenReturn(
				mockExtendingWonRoundCount);

		GameFactory gameFactory = testObject.createGameFactory(
				mockToRoundCount, new String[] { "", "", "-by", "foo" });

		assertEquals(new GameFactory(mockWinningWonRoundCount,
				new ToByAfterPlayHookFactory(mockExtendingWonRoundCount,
						mockWinningWonRoundCount), mockAttemptReader),
				gameFactory);
	}
}

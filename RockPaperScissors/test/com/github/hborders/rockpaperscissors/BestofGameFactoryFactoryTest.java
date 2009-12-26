package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofGameFactoryFactoryTest {
	private BestofWonRoundCountFactory mockBestofWonRoundCountFactory;
	private GameFactory.Provider mockGameFactoryProvider;
	private Game.Provider mockGameProvider;
	private Round mockRound;

	private BestofGameFactoryFactory testObject;

	private WonRoundCount mockBestofWonRoundCount;
	private GameFactory mockGameFactory;

	@Before
	public void setup() {
		mockBestofWonRoundCountFactory = mock(BestofWonRoundCountFactory.class);
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockGameProvider = mock(Game.Provider.class);
		mockRound = mock(Round.class);

		testObject = new BestofGameFactoryFactory(
				mockBestofWonRoundCountFactory, mockGameFactoryProvider,
				mockGameProvider, mockRound);

		mockBestofWonRoundCount = mock(WonRoundCount.class);
		mockGameFactory = mock(GameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_not_2()
			throws Exception {
		testObject.createGameFactory(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_BestofWonRoundCountFactory_throws_InvalidWonRoundCountException()
			throws Exception {
		when(mockBestofWonRoundCountFactory.createWonRoundCount("foo"))
				.thenThrow(new InvalidWonRoundCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGameFactory_returns_GameFactory_from_GameFactory_Provider_when_args_length_is_2_and_BestofWonRoundCountFactory_returns_bestof_WonRoundCount()
			throws Exception {
		when(mockBestofWonRoundCountFactory.createWonRoundCount("foo"))
				.thenReturn(mockBestofWonRoundCount);
		when(
				mockGameFactoryProvider.provide(mockBestofWonRoundCount,
						mockRound, mockGameProvider)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}
}

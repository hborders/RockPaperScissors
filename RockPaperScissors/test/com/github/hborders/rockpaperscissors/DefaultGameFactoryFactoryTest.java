package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DefaultGameFactoryFactoryTest {
	private GameFactory.Provider mockDefaultGameFactoryProvider;
	private Round mockDefaultRound;
	private WonRoundCount mockDefaultWinningWonRoundCount;
	private Game.Provider mockGameProvider;
	private ToGameFactoryFactory mockToGameFactoryFactory;
	private BestofGameFactoryFactory mockBestofGameFactoryFactory;

	private DefaultGameFactoryFactory testObject;

	private GameFactory mockGameFactory;

	@Before
	public void setup() {
		mockDefaultGameFactoryProvider = mock(GameFactory.Provider.class);
		mockDefaultRound = mock(Round.class);
		mockDefaultWinningWonRoundCount = mock(WonRoundCount.class);
		mockGameProvider = mock(Game.Provider.class);

		mockToGameFactoryFactory = mock(ToGameFactoryFactory.class);
		mockBestofGameFactoryFactory = mock(BestofGameFactoryFactory.class);

		testObject = new DefaultGameFactoryFactory(
				mockDefaultGameFactoryProvider, mockDefaultRound,
				mockDefaultWinningWonRoundCount, mockGameProvider,
				mockToGameFactoryFactory, mockBestofGameFactoryFactory);

		mockGameFactory = mock(GameFactory.class);
	}

	@Test
	public void createGameFactory_returns_GameFactory_from_GameFactory_Provider_when_args_is_empty()
			throws Exception {
		when(
				mockDefaultGameFactoryProvider.provide(
						mockDefaultWinningWonRoundCount, mockDefaultRound, mockGameProvider))
				.thenReturn(mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[0]);

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToGameFactoryFactory_when_to_is_first_arg()
			throws Exception {
		String[] args = { "-to" };
		when(mockToGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_BestofGameFactoryFactory_when_bestof_is_first_arg()
			throws Exception {
		String[] args = { "-bestof" };
		when(mockBestofGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_first_arg_is_not_to_or_bestof()
			throws Exception {
		testObject.createGameFactory(new String[] { "foo" });
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_is_null()
			throws Exception {
		testObject.createGameFactory(null);
	}
}

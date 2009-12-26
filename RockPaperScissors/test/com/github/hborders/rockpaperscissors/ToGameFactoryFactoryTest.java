package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToGameFactoryFactoryTest {
	private GameCountFactory mockGameCountFactory;
	private ToWonRoundCountFactory mockToWonRoundCountFactory;
	private ToByGameFactoryFactory mockToByGameFactoryFactory;
	private Game.Provider mockGameProvider;
	private Round mockRound;
	private GameFactory.Provider mockToGameFactoryProvider;

	private ToGameFactoryFactory testObject;

	private GameCount mockToGameCount;
	private WonRoundCount mockWonRoundCount;
	private GameFactory mockGameFactory;

	@Before
	public void setup() {
		mockGameCountFactory = mock(GameCountFactory.class);
		mockToWonRoundCountFactory = mock(ToWonRoundCountFactory.class);
		mockToByGameFactoryFactory = mock(ToByGameFactoryFactory.class);
		mockGameProvider = mock(Game.Provider.class);
		mockRound = mock(Round.class);
		mockToGameFactoryProvider = mock(GameFactory.Provider.class);

		testObject = new ToGameFactoryFactory(mockGameCountFactory,
				mockToWonRoundCountFactory, mockToByGameFactoryFactory,
				mockGameProvider, mockRound, mockToGameFactoryProvider);

		mockToGameCount = mock(GameCount.class);
		mockWonRoundCount = mock(WonRoundCount.class);
		mockGameFactory = mock(GameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		testObject.createGameFactory(new String[1]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountFactory_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGame_returns_GameFactory_from_GameFactory_Provider_with_winning_WonRoundCount_from_GameCount()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenReturn(
				mockToGameCount);
		when(mockToWonRoundCountFactory.createWonRoundCount(mockToGameCount))
				.thenReturn(mockWonRoundCount);
		when(
				mockToGameFactoryProvider.provide(mockWonRoundCount, mockRound,
						mockGameProvider)).thenReturn(mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToByGameFactoryFactory_when_3rd_argument_is_by()
			throws Exception {
		String[] args = { "", "foo", "-by" };
		when(mockGameCountFactory.createGameCount("foo")).thenReturn(
				mockToGameCount);
		when(
				mockToByGameFactoryFactory.createGameFactory(mockToGameCount,
						args)).thenReturn(mockGameFactory);
		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);
	}
}

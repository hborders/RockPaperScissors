package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DefaultGameFactoryTest {
	private Round mockRound;
	private WonRoundCount.Provider mockWonRoundCountProvider;
	private Game.Provider mockGameProvider;

	private DefaultGameFactory testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private WonRoundCount mockWonRoundCount;
	private Game mockGame;

	@Before
	public void setup() {
		mockRound = mock(Round.class);
		mockWonRoundCountProvider = mock(WonRoundCount.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new DefaultGameFactory(mockRound,
				mockWonRoundCountProvider, mockGameProvider);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		mockGame = mock(Game.class);
		mockWonRoundCount = mock(WonRoundCount.class);
	}

	@Test
	public void createGame_returns_Game_with_WonRoundCount_of_1_and_given_Round()
			throws Exception {
		when(mockWonRoundCountProvider.provide(1))
				.thenReturn(mockWonRoundCount);
		when(
				mockGameProvider.provide(mockWonRoundCount, mockFirstPlayer,
						mockSecondPlayer, mockRound)).thenReturn(mockGame);

		Game game = testObject.createGame(mockFirstPlayer, mockSecondPlayer);

		assertEquals(mockGame, game);
	}
}

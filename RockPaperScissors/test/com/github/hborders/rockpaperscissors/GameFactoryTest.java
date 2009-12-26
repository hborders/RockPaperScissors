package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GameFactoryTest {
	private WonRoundCount mockWinningWonRoundCount;
	private Round mockRound;
	private Game.Provider mockGameProvider;

	private GameFactory testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private Game mockGame;

	@Before
	public void setup() {
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockRound = mock(Round.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new GameFactory(mockWinningWonRoundCount, mockRound,
				mockGameProvider);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		mockGame = mock(Game.class);
	}

	@Test
	public void createGame_passes_winning_WonRoundCount_to_Game_Provider()
			throws Exception {
		when(
				mockGameProvider.provide(mockWinningWonRoundCount,
						mockFirstPlayer, mockSecondPlayer, mockRound))
				.thenReturn(mockGame);

		Game game = testObject.createGame(mockFirstPlayer, mockSecondPlayer);

		assertEquals(mockGame, game);
	}
}

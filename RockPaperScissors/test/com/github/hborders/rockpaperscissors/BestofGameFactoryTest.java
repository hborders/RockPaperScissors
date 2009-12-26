package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class BestofGameFactoryTest {
	private Game.Provider mockGameProvider;
	private WonRoundCount mockWinningWonRoundCount;
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round mockRound;

	@Before
	public void setup() {
		mockGameProvider = mock(Game.Provider.class);
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRound = mock(Round.class);
	}

	@Test
	public void createGame_passes_winning_WonRoundCount_to_Game_Provider()
			throws Exception {

		Game mockGame = mock(Game.class);
		when(
				mockGameProvider.provide(mockWinningWonRoundCount,
						mockFirstPlayer, mockSecondPlayer, mockRound))
				.thenReturn(mockGame);

		BestofGameFactory testObject = new BestofGameFactory(mockGameProvider,
				mockWinningWonRoundCount, mockRound);

		Game game = testObject.createGame(mockFirstPlayer, mockSecondPlayer);

		assertEquals(mockGame, game);
	}
}

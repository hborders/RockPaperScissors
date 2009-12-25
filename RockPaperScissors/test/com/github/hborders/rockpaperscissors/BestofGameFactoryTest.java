package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class BestofGameFactoryTest {
	private BestofGame.Provider mockBestofGameProvider;
	private WonRoundCount mockWinningWonRoundCount;
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round mockRound;

	@Before
	public void setup() {
		mockBestofGameProvider = mock(BestofGame.Provider.class);
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRound = mock(Round.class);
	}

	@Test
	public void createGame_passes_winning_WonRoundCount_to_BestofGame_Provider()
			throws Exception {

		BestofGame mockBestofGame = mock(BestofGame.class);
		when(
				mockBestofGameProvider.provide(mockWinningWonRoundCount,
						mockFirstPlayer, mockSecondPlayer, mockRound))
				.thenReturn(mockBestofGame);

		BestofGameFactory testObject = new BestofGameFactory(
				mockBestofGameProvider, mockWinningWonRoundCount, mockRound);

		BestofGame bestofGame = testObject.createGame(mockFirstPlayer,
				mockSecondPlayer);

		assertEquals(mockBestofGame, bestofGame);
	}
}

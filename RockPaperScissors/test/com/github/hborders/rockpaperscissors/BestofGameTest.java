package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class BestofGameTest {
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round.Provider mockRoundProvider;
	private AttemptReader mockAttemptReader;

	private BestofGame testObject;

	private Round mockFirstRound;
	private Round mockSecondRound;
	private Round mockThirdRound;

	@Before
	public void setup() {
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockAttemptReader = mock(AttemptReader.class);

		testObject = new BestofGame(3, mockFirstPlayer, mockSecondPlayer,
				mockRoundProvider);

		mockFirstRound = mock(Round.class);
		mockSecondRound = mock(Round.class);
		mockThirdRound = mock(Round.class);

		when(
				mockRoundProvider.provide(mockFirstPlayer, mockSecondPlayer,
						mockAttemptReader)).thenReturn(mockFirstRound)
				.thenReturn(mockSecondRound).thenReturn(mockThirdRound);
	}

	@Test
	public void play_returns_winner_after_winning_in_minimum_games()
			throws Exception {
		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);

		verify(mockRoundProvider.provide(mockFirstPlayer, mockSecondPlayer,
				mockAttemptReader), times(2));
	}

	@Test
	public void play_returns_winner_after_winning_in_maximum_games()
			throws Exception {
		Player winningPlayer = testObject.play();

		assertEquals(mockSecondPlayer, winningPlayer);

		verify(mockRoundProvider.provide(mockFirstPlayer, mockSecondPlayer,
				mockAttemptReader), times(3));
	}
}

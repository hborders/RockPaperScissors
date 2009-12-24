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
				mockRoundProvider, mockAttemptReader);

		mockFirstRound = mock(Round.class);
		mockSecondRound = mock(Round.class);
		mockThirdRound = mock(Round.class);

		when(
				mockRoundProvider.provide(mockFirstPlayer, mockSecondPlayer,
						mockAttemptReader)).thenReturn(mockFirstRound)
				.thenReturn(mockSecondRound).thenReturn(mockThirdRound);
	}

	@Test
	public void play_creates_and_plays_minimum_Rounds_if_winner_wins_consecutively()
			throws Exception {
		when(mockFirstPlayer.getWins()).thenReturn(0).thenReturn(1).thenReturn(
				2).thenThrow(new RuntimeException());
		when(mockSecondPlayer.getWins()).thenReturn(0).thenReturn(0)
				.thenReturn(0).thenThrow(new RuntimeException());

		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);

		verify(mockRoundProvider, times(2)).provide(mockFirstPlayer,
				mockSecondPlayer, mockAttemptReader);
		verify(mockFirstRound).play();
		verify(mockSecondRound).play();
	}

	@Test
	public void play_creates_and_plays_maximum_Rounds_if_winner_and_loser_separated_by_one_Round()
			throws Exception {
		when(mockFirstPlayer.getWins()).thenReturn(0).thenReturn(1).thenReturn(
				1).thenReturn(1).thenThrow(new RuntimeException());
		when(mockSecondPlayer.getWins()).thenReturn(0).thenReturn(0)
				.thenReturn(1).thenReturn(2).thenThrow(new RuntimeException());

		Player winningPlayer = testObject.play();

		assertEquals(mockSecondPlayer, winningPlayer);

		verify(mockRoundProvider, times(3)).provide(mockFirstPlayer,
				mockSecondPlayer, mockAttemptReader);
		verify(mockFirstRound).play();
		verify(mockSecondRound).play();
		verify(mockThirdRound).play();
	}
}

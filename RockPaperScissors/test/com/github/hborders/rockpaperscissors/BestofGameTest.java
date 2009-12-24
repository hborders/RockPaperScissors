package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class BestofGameTest {
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round mockRound;

	private BestofGame testObject;

	@Before
	public void setup() {
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRound = mock(Round.class);

		testObject = new BestofGame(3, mockFirstPlayer, mockSecondPlayer,
				mockRound);
	}

	@Test
	public void play_plays_minimum_Rounds_if_winner_wins_consecutively()
			throws Exception {
		when(mockFirstPlayer.getWins()).thenReturn(0).thenReturn(1).thenReturn(
				2).thenThrow(new RuntimeException());
		when(mockSecondPlayer.getWins()).thenReturn(0).thenReturn(0)
				.thenReturn(0).thenThrow(new RuntimeException());

		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);

		verify(mockRound, times(2)).play(mockFirstPlayer, mockSecondPlayer);
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

		verify(mockRound, times(3)).play(mockFirstPlayer, mockSecondPlayer);
	}
}

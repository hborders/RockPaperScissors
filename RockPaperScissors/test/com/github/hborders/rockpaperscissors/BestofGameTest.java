package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class BestofGameTest {
	private WonRoundCount mockWinningWonRoundCount;
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round mockRound;

	private BestofGame testObject;

	private WonRoundCount mockFirstPlayerWonRoundCount;
	private WonRoundCount mockSecondPlayerWonRoundCount;

	@Before
	public void setup() {
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRound = mock(Round.class);

		testObject = new BestofGame(mockWinningWonRoundCount, mockFirstPlayer,
				mockSecondPlayer, mockRound);

		mockFirstPlayerWonRoundCount = mock(WonRoundCount.class);
		mockSecondPlayerWonRoundCount = mock(WonRoundCount.class);

		when(mockFirstPlayer.getWonRoundCount()).thenReturn(
				mockFirstPlayerWonRoundCount);
		when(mockSecondPlayer.getWonRoundCount()).thenReturn(
				mockSecondPlayerWonRoundCount);
	}

	@Test
	public void play_does_not_play_Round_if_first_Player_WonRoundCount_equals_winning_WonRoundCount()
			throws Exception {
		when(mockWinningWonRoundCount.matches(mockFirstPlayerWonRoundCount))
				.thenReturn(true).thenThrow(
						new RuntimeException("Possible infinite loop"));
		when(mockWinningWonRoundCount.matches(mockSecondPlayerWonRoundCount))
				.thenReturn(false).thenThrow(
						new RuntimeException("Possible infinite loop"));

		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);

		verify(mockRound, times(0)).play(mockFirstPlayer, mockSecondPlayer);
	}

	@Test
	public void play_does_not_play_Round_if_second_Player_WonRoundCount_equals_winning_WonRoundCount()
			throws Exception {
		when(mockWinningWonRoundCount.matches(mockFirstPlayerWonRoundCount))
				.thenReturn(false).thenThrow(
						new RuntimeException("Possible infinite loop"));
		when(mockWinningWonRoundCount.matches(mockSecondPlayerWonRoundCount))
				.thenReturn(true).thenThrow(
						new RuntimeException("Possible infinite loop"));

		Player winningPlayer = testObject.play();

		assertEquals(mockSecondPlayer, winningPlayer);

		verify(mockRound, times(0)).play(mockFirstPlayer, mockSecondPlayer);
	}

	@Test
	public void play_plays_Round_if_neither_Players_WonRoundCount_equals_winning_WonRoundCount()
			throws Exception {
		when(mockWinningWonRoundCount.matches(mockFirstPlayerWonRoundCount))
				.thenReturn(false).thenReturn(false).thenThrow(
						new RuntimeException("Possible infinite loop"));
		when(mockWinningWonRoundCount.matches(mockSecondPlayerWonRoundCount))
				.thenReturn(false).thenReturn(true).thenThrow(
						new RuntimeException("Possible infinite loop"));

		Player winningPlayer = testObject.play();

		assertEquals(mockSecondPlayer, winningPlayer);

		verify(mockRound, times(1)).play(mockFirstPlayer, mockSecondPlayer);
	}
}

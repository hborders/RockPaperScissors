package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;

public class ToByGameTest {
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round mockRound;

	private ToByGame testObject;

	@Before
	public void setup() {
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRound = mock(Round.class);
	}

	// @Test
	public void play_plays_Round_once_for_to_1_by_1() throws Exception {
		testObject = new ToByGame();

		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);

		verify(mockRound).play(mockFirstPlayer, mockSecondPlayer);
	}

}

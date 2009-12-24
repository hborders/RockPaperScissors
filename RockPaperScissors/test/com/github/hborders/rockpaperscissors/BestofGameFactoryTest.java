package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofGameFactory.InvalidGameCountException;

public class BestofGameFactoryTest {
	private BestofGame.Provider mockBestofGameProvider;
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Round mockRound;

	@Before
	public void setup() {
		mockBestofGameProvider = mock(BestofGame.Provider.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRound = mock(Round.class);
	}

	@Test(expected = InvalidGameCountException.class)
	public void constructor_throws_InvalidGameCountException_when_gameCount_is_even()
			throws Exception {
		new BestofGameFactory(2, mockBestofGameProvider, mockRound);
	}

	@Test
	public void createGame_passes_gameCount_to_BestofGame_Provider()
			throws Exception {

		BestofGame mockBestofGame = mock(BestofGame.class);

		when(
				mockBestofGameProvider.provide(3, mockFirstPlayer,
						mockSecondPlayer, mockRound))
				.thenReturn(mockBestofGame);

		BestofGameFactory testObject = new BestofGameFactory(3,
				mockBestofGameProvider, mockRound);

		BestofGame bestofGame = testObject.createGame(mockFirstPlayer,
				mockSecondPlayer);

		assertEquals(mockBestofGame, bestofGame);
	}
}

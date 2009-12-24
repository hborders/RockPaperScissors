package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofGameFactory.InvalidGameCountException;

public class BestofGameFactoryTest {
	private BestofGame.Provider mockBestofGameProvider;

	@Before
	public void setup() {
		mockBestofGameProvider = mock(BestofGame.Provider.class);
	}

	@Test(expected = InvalidGameCountException.class)
	public void constructor_throws_InvalidGameCountException_when_gameCount_is_even()
			throws Exception {
		new BestofGameFactory(2, mockBestofGameProvider);
	}

	@Test
	public void createGame_passes_gameCount_to_BestofGame_Provider()
			throws Exception {
		Player mockFirstPlayer = mock(Player.class);
		Player mockSecondPlayer = mock(Player.class);
		Round.Provider mockRoundProvider = mock(Round.Provider.class);
		AttemptReader mockAttemptReader = mock(AttemptReader.class);

		BestofGame mockBestofGame = mock(BestofGame.class);

		when(
				mockBestofGameProvider.provide(3, mockFirstPlayer,
						mockSecondPlayer, mockRoundProvider, mockAttemptReader))
				.thenReturn(mockBestofGame);

		BestofGameFactory testObject = new BestofGameFactory(3,
				mockBestofGameProvider);

		BestofGame bestofGame = testObject.createGame(mockFirstPlayer,
				mockSecondPlayer);

		assertEquals(mockBestofGame, bestofGame);
	}
}

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
	private Round.Provider mockRoundProvider;
	private AttemptReader mockAttemptReader;

	@Before
	public void setup() {
		mockBestofGameProvider = mock(BestofGame.Provider.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockAttemptReader = mock(AttemptReader.class);
	}

	@Test(expected = InvalidGameCountException.class)
	public void constructor_throws_InvalidGameCountException_when_gameCount_is_even()
			throws Exception {
		new BestofGameFactory(2, mockBestofGameProvider, mockRoundProvider,
				mockAttemptReader);
	}

	@Test
	public void createGame_passes_gameCount_to_BestofGame_Provider()
			throws Exception {

		BestofGame mockBestofGame = mock(BestofGame.class);

		when(
				mockBestofGameProvider.provide(3, mockFirstPlayer,
						mockSecondPlayer, mockRoundProvider, mockAttemptReader))
				.thenReturn(mockBestofGame);

		BestofGameFactory testObject = new BestofGameFactory(3,
				mockBestofGameProvider, mockRoundProvider, mockAttemptReader);

		BestofGame bestofGame = testObject.createGame(mockFirstPlayer,
				mockSecondPlayer);

		assertEquals(mockBestofGame, bestofGame);
	}
}

package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class RockPaperScissorsTest {

	private UsagePrinter mockUsagePrinter;
	private DefaultGameFactory mockDefaultGameFactory;
	private PlayerFactory mockPlayerFactory;
	private RockPaperScissors testObject;

	private Game mockGame;
	private Player mockPlayer1;
	private Player mockPlayer2;

	@Before
	public void setUp() {
		mockUsagePrinter = mock(UsagePrinter.class);
		mockDefaultGameFactory = mock(DefaultGameFactory.class);
		mockPlayerFactory = mock(PlayerFactory.class);

		testObject = new RockPaperScissors(mockUsagePrinter,
				mockDefaultGameFactory, mockPlayerFactory);

		mockGame = mock(Game.class);
		mockPlayer1 = mock(Player.class);
		mockPlayer2 = mock(Player.class);
	}

	@Test
	public void play_prints_usage_when_DefaultGameFactory_throws_InvalidGameArgumentsException()
			throws Exception {
		when(mockDefaultGameFactory.createGame(new String[0])).thenThrow(
				new InvalidGameArgumentsException());

		testObject.play(new String[0]);

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_creates_Game_and_two_Players() throws Exception {
		when(mockDefaultGameFactory.createGame(new String[0])).thenReturn(
				mockGame);
		when(mockPlayerFactory.createPlayer(1)).thenReturn(mockPlayer1);
		when(mockPlayerFactory.createPlayer(2)).thenReturn(mockPlayer2);

		testObject.play(new String[0]);

		verify(mockDefaultGameFactory).createGame(new String[0]);
		verify(mockPlayerFactory).createPlayer(1);
		verify(mockPlayerFactory).createPlayer(2);
	}
}

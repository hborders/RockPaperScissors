package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class RockPaperScissorsTest {

	private UsagePrinter mockUsagePrinter;
	private DefaultGameFactoryFactory mockDefaultGameFactoryFactory;
	private PlayerFactory mockPlayerFactory;
	private RockPaperScissors testObject;

	private GameFactory mockGameFactory;
	private Player mockPlayer1;
	private Player mockPlayer2;
	private Game mockGame;

	@Before
	public void setUp() {
		mockUsagePrinter = mock(UsagePrinter.class);
		mockDefaultGameFactoryFactory = mock(DefaultGameFactoryFactory.class);
		mockPlayerFactory = mock(PlayerFactory.class);

		testObject = new RockPaperScissors(mockUsagePrinter,
				mockDefaultGameFactoryFactory, mockPlayerFactory);

		mockGameFactory = mock(GameFactory.class);
		mockPlayer1 = mock(Player.class);
		mockPlayer2 = mock(Player.class);
		mockGame = mock(Game.class);
	}

	@Test
	public void play_prints_usage_when_DefaultGameFactory_throws_InvalidGameArgumentsException()
			throws Exception {
		when(mockDefaultGameFactoryFactory.createGameFactory(new String[0]))
				.thenThrow(new InvalidGameArgumentsException());

		testObject.play(new String[0]);

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_printsStackTrace_of_IOException_thrown_when_playing_Game()
			throws Exception {
		when(mockDefaultGameFactoryFactory.createGameFactory(new String[0]))
				.thenReturn(mockGameFactory);
		when(mockPlayerFactory.createPlayer(1)).thenReturn(mockPlayer1);
		when(mockPlayerFactory.createPlayer(2)).thenReturn(mockPlayer2);
		when(mockGameFactory.createGame(mockPlayer1, mockPlayer2)).thenReturn(
				mockGame);
		IOException mockIOException = mock(IOException.class);
		when(mockGame.play()).thenThrow(mockIOException);

		testObject.play(new String[0]);

		verify(mockIOException).printStackTrace();
	}

	@Test
	public void play_creates_GameFactory_and_two_Players_and_Game_then_plays_Game()
			throws Exception {
		when(mockDefaultGameFactoryFactory.createGameFactory(new String[0]))
				.thenReturn(mockGameFactory);
		when(mockPlayerFactory.createPlayer(1)).thenReturn(mockPlayer1);
		when(mockPlayerFactory.createPlayer(2)).thenReturn(mockPlayer2);
		when(mockGameFactory.createGame(mockPlayer1, mockPlayer2)).thenReturn(
				mockGame);

		testObject.play(new String[0]);

		verify(mockGame).play();
	}
}

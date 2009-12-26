package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

public class RockPaperScissorsTest {

	private UsagePrinter mockUsagePrinter;
	private DefaultGameFactoryFactory mockDefaultGameFactoryFactory;
	private PlayerFactory mockPlayerFactory;
	private PrintWriter mockPrintWriter;

	private RockPaperScissors testObject;

	private GameFactory mockGameFactory;
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private Game mockGame;
	private Player mockWinningPlayer;

	@Before
	public void setUp() {
		mockUsagePrinter = mock(UsagePrinter.class);
		mockDefaultGameFactoryFactory = mock(DefaultGameFactoryFactory.class);
		mockPlayerFactory = mock(PlayerFactory.class);
		mockPrintWriter = mock(PrintWriter.class);

		testObject = new RockPaperScissors(mockUsagePrinter,
				mockDefaultGameFactoryFactory, mockPlayerFactory,
				mockPrintWriter);

		mockGameFactory = mock(GameFactory.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockGame = mock(Game.class);
		mockWinningPlayer = mock(Player.class);
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
		when(mockPlayerFactory.createPlayer(1)).thenReturn(mockFirstPlayer);
		when(mockPlayerFactory.createPlayer(2)).thenReturn(mockSecondPlayer);
		when(mockGameFactory.createGame(mockFirstPlayer, mockSecondPlayer))
				.thenReturn(mockGame);
		IOException mockIOException = mock(IOException.class);
		when(mockGame.play()).thenThrow(mockIOException);

		testObject.play(new String[0]);

		verify(mockIOException).printStackTrace();
	}

	@Test
	public void play_creates_GameFactory_and_two_Players_and_Game_then_plays_Game_then_writes_winning_Player()
			throws Exception {
		when(mockDefaultGameFactoryFactory.createGameFactory(new String[0]))
				.thenReturn(mockGameFactory);
		when(mockPlayerFactory.createPlayer(1)).thenReturn(mockFirstPlayer);
		when(mockPlayerFactory.createPlayer(2)).thenReturn(mockSecondPlayer);
		when(mockGameFactory.createGame(mockFirstPlayer, mockSecondPlayer))
				.thenReturn(mockGame);
		when(mockGame.play()).thenReturn(mockWinningPlayer);

		testObject.play(new String[0]);

		InOrder inOrder = inOrder(mockPrintWriter, mockWinningPlayer);
		inOrder.verify(mockWinningPlayer).write(mockPrintWriter);
		inOrder.verify(mockPrintWriter).println(" Wins!");
		inOrder.verify(mockPrintWriter).flush();
	}
}

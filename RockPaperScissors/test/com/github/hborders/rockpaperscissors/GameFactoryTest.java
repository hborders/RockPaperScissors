package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;
import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class GameFactoryTest {
	private WonRoundCount mockWinningWonRoundCount;
	private IAfterPlayHookFactory mockAfterPlayHookFactory;
	private AttemptReader mockAttemptReader;

	private GameFactory testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private IAfterPlayHook mockAfterPlayHook;

	@Before
	public void setup() {
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockAfterPlayHookFactory = mock(IAfterPlayHookFactory.class);
		mockAttemptReader = mock(AttemptReader.class);

		testObject = new GameFactory(mockWinningWonRoundCount,
				mockAfterPlayHookFactory, mockAttemptReader);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		mockAfterPlayHook = mock(IAfterPlayHook.class);
	}

	@Test
	public void createGame_passes_creates_IAfterPlayHook_then_Round_then_Game()
			throws Exception {
		when(
				mockAfterPlayHookFactory.createAfterPlayHook(mockFirstPlayer,
						mockSecondPlayer)).thenReturn(mockAfterPlayHook);

		Game game = testObject.createGame(mockFirstPlayer, mockSecondPlayer);

		assertEquals(new Game(mockWinningWonRoundCount, mockFirstPlayer,
				mockSecondPlayer, new Round(mockAttemptReader,
						mockAfterPlayHook)), game);
	}
}

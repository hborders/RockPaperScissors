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
	private Round.Provider mockRoundProvider;
	private Game.Provider mockGameProvider;

	private GameFactory testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private IAfterPlayHook mockAfterPlayHook;
	private Round mockRound;
	private Game mockGame;

	@Before
	public void setup() {
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockAfterPlayHookFactory = mock(IAfterPlayHookFactory.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new GameFactory(mockWinningWonRoundCount,
				mockAfterPlayHookFactory, mockAttemptReader, mockRoundProvider,
				mockGameProvider);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		mockAfterPlayHook = mock(IAfterPlayHook.class);
		mockRound = mock(Round.class);
		mockGame = mock(Game.class);
	}

	@Test
	public void createGame_passes_creates_IAfterPlayHook_then_Round_then_Game()
			throws Exception {
		when(
				mockAfterPlayHookFactory.createAfterPlayHook(mockFirstPlayer,
						mockSecondPlayer)).thenReturn(mockAfterPlayHook);
		when(mockRoundProvider.provide(mockAttemptReader, mockAfterPlayHook))
				.thenReturn(mockRound);
		when(
				mockGameProvider.provide(mockWinningWonRoundCount,
						mockFirstPlayer, mockSecondPlayer, mockRound))
				.thenReturn(mockGame);

		Game game = testObject.createGame(mockFirstPlayer, mockSecondPlayer);

		assertEquals(mockGame, game);
	}
}

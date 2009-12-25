package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerFactoryTest {
	private Console mockConsole;
	private Player.Provider mockPlayerProvider;
	private WonRoundCount.Provider mockWonRoundCountProvider;

	private PlayerFactory testObject;

	private WonRoundCount mockWonRoundCount;

	private Player mockPlayer;

	@Before
	public void setup() {
		mockConsole = mock(Console.class);
		mockPlayerProvider = mock(Player.Provider.class);
		mockWonRoundCountProvider = mock(WonRoundCount.Provider.class);

		testObject = new PlayerFactory(mockConsole, mockWonRoundCountProvider,
				mockPlayerProvider);

		mockWonRoundCount = mock(WonRoundCount.class);

		mockPlayer = mock(Player.class);
	}

	@Test
	public void createPlayer_prints_prompt_and_trims_input_and_returns_Player_from_PlayerProvider()
			throws Exception {
		when(mockConsole.readLine("Player %d Name: ", 1)).thenReturn("  foo  ")
				.thenThrow(new RuntimeException("Possible infinite loop"));
		when(mockWonRoundCountProvider.provide(0))
				.thenReturn(mockWonRoundCount);
		when(mockPlayerProvider.provide("foo", 1, mockWonRoundCount))
				.thenReturn(mockPlayer);

		Player player = testObject.createPlayer(1);

		assertEquals(mockPlayer, player);
	}

	@Test
	public void createPlayer_reprompts_when_input_is_null() {
		when(mockConsole.readLine("Player %d Name: ", 1)).thenReturn(null)
				.thenReturn("bar").thenThrow(
						new RuntimeException("Possible infinite loop"));
		when(mockWonRoundCountProvider.provide(0))
				.thenReturn(mockWonRoundCount);
		when(mockPlayerProvider.provide("bar", 1, mockWonRoundCount))
				.thenReturn(mockPlayer);

		Player player = testObject.createPlayer(1);

		assertEquals(mockPlayer, player);
	}

	@Test
	public void createPlayer_reprompts_when_trimmed_input_is_empty()
			throws Exception {
		when(mockConsole.readLine("Player %d Name: ", 1)).thenReturn("   ")
				.thenReturn("bar").thenThrow(
						new RuntimeException("Possible infinite loop"));
		when(mockWonRoundCountProvider.provide(0))
				.thenReturn(mockWonRoundCount);
		when(mockPlayerProvider.provide("bar", 1, mockWonRoundCount))
				.thenReturn(mockPlayer);

		Player player = testObject.createPlayer(1);

		assertEquals(mockPlayer, player);
	}
}

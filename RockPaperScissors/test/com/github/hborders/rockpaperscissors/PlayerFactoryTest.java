package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.Player.InvalidPlayerException;

public class PlayerFactoryTest {
	private Console mockConsole;
	private Player.Provider mockPlayerProvider;

	private PlayerFactory testObject;

	private Player mockPlayer;

	@Before
	public void setup() {
		mockConsole = mock(Console.class);
		mockPlayerProvider = mock(Player.Provider.class);

		testObject = new PlayerFactory(mockConsole, mockPlayerProvider);

		mockPlayer = mock(Player.class);
	}

	@Test
	public void createPlayer_prints_prompt_and_returns_Player_from_PlayerProvider()
			throws Exception {
		when(mockConsole.readLine("Player %d Name", 1)).thenReturn("foo");
		when(mockPlayerProvider.provide("foo")).thenReturn(mockPlayer);

		Player player = testObject.createPlayer(1);

		assertEquals(mockPlayer, player);
	}

	@Test
	public void createPlayer_reprompts_when_PlayerProvider_throws_InvalidPlayerException()
			throws Exception {
		when(mockConsole.readLine("Player %d Name", 1)).thenReturn("foo");
		when(mockPlayerProvider.provide("foo")).thenThrow(
				new InvalidPlayerException());

		when(mockConsole.readLine("Player %d Name", 1)).thenReturn("bar");
		when(mockPlayerProvider.provide("bar")).thenReturn(mockPlayer);

		Player player = testObject.createPlayer(1);

		assertEquals(mockPlayer, player);
	}
}

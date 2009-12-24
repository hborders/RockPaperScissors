package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class AttemptFactoryTest {

	private Console mockConsole;

	private AttemptFactory testObject;

	private Player mockPlayer;

	private Attempt mockAttempt;

	@Before
	public void setup() {
		mockConsole = mock(Console.class);

		testObject = new AttemptFactory(mockConsole);

		mockPlayer = mock(Player.class);
		mockAttempt = mock(Attempt.class);
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_ROCK_Attempt_for_uppercase_r() {

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.ROCK, null);
	}
}

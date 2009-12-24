package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

public class AttemptFactoryTest {

	private Writer mockWriter;
	private Console mockConsole;

	private AttemptFactory testObject;

	private Player mockPlayer;

	private InOrder inOrder;

	@Before
	public void setup() {
		mockWriter = mock(Writer.class);
		mockConsole = mock(Console.class);

		testObject = new AttemptFactory(mockWriter, mockConsole);

		mockPlayer = mock(Player.class);

		inOrder = inOrder(mockPlayer, mockWriter, mockConsole);
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_ROCK_Attempt_for_uppercase_r()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("R");

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.ROCK, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_ROCK_Attempt_for_lowercase_r()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("r");

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.ROCK, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_PAPER_Attempt_for_uppercase_p()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("P");

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.PAPER, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_PAPER_Attempt_for_lowercase_p()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("p");

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.PAPER, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_PAPER_Attempt_for_uppercase_s()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("S");

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.SCISSORS, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}

	@Test
	public void createAttempt_printsPrompt_and_returns_PAPER_Attempt_for_lowercase_s()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("s");

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(Attempt.SCISSORS, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}
}

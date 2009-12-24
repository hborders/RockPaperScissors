package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.github.hborders.rockpaperscissors.AttemptFactory.InvalidRawAttemptException;

public class AttemptReaderTest {

	private Writer mockWriter;
	private Console mockConsole;
	private AttemptFactory mockAttemptFactory;

	private AttemptReader testObject;

	private Player mockPlayer;

	private Attempt mockAttempt;

	private InOrder inOrder;

	@Before
	public void setup() {
		mockWriter = mock(Writer.class);
		mockConsole = mock(Console.class);
		mockAttemptFactory = mock(AttemptFactory.class);

		testObject = new AttemptReader(mockWriter, mockConsole,
				mockAttemptFactory);

		mockPlayer = mock(Player.class);

		mockAttempt = mock(Attempt.class);

		inOrder = inOrder(mockPlayer, mockWriter, mockConsole);
	}

	@Test
	public void createAttempt_prints_prompt_and_returns_Attempt_from_AttemptFactory()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("foo");
		when(mockAttemptFactory.createAttempt("foo")).thenReturn(mockAttempt);

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(mockAttempt, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}

	@Test
	public void createAttempt_prints_new_prompt_when_AttemptFactory_throws_InvalidRawAttemptException()
			throws Exception {
		when(mockConsole.readLine()).thenReturn("bar").thenReturn("foo");
		when(mockAttemptFactory.createAttempt("foo")).thenThrow(
				new InvalidRawAttemptException()).thenReturn(mockAttempt);

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(mockAttempt, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockConsole).readLine();
	}
}

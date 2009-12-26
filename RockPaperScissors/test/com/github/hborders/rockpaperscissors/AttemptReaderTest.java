package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.github.hborders.rockpaperscissors.AttemptFactory.InvalidRawAttemptException;

public class AttemptReaderTest {

	private BufferedReader mockBufferedReader;
	private Writer mockWriter;
	private AttemptFactory mockAttemptFactory;

	private AttemptReader testObject;

	private Player mockPlayer;

	private Attempt mockAttempt;

	private InOrder inOrder;

	@Before
	public void setup() {
		mockWriter = mock(Writer.class);
		mockBufferedReader = mock(BufferedReader.class);
		mockAttemptFactory = mock(AttemptFactory.class);

		testObject = new AttemptReader(mockBufferedReader, mockWriter,
				mockAttemptFactory);

		mockPlayer = mock(Player.class);

		mockAttempt = mock(Attempt.class);

		inOrder = inOrder(mockBufferedReader, mockWriter, mockPlayer);
	}

	@Test
	public void createAttempt_prints_prompt_and_returns_Attempt_from_AttemptFactory()
			throws Exception {
		when(mockBufferedReader.readLine()).thenReturn("foo");
		when(mockAttemptFactory.createAttempt("foo")).thenReturn(mockAttempt);

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(mockAttempt, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockBufferedReader).readLine();
	}

	@Test
	public void createAttempt_prints_new_prompt_when_AttemptFactory_throws_InvalidRawAttemptException()
			throws Exception {
		when(mockBufferedReader.readLine()).thenReturn("bar").thenReturn("foo");
		when(mockAttemptFactory.createAttempt("bar")).thenThrow(
				new InvalidRawAttemptException());
		when(mockAttemptFactory.createAttempt("foo")).thenReturn(mockAttempt);

		Attempt attempt = testObject.createAttempt(mockPlayer);

		assertEquals(mockAttempt, attempt);

		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockBufferedReader).readLine();
		inOrder.verify(mockPlayer).write(mockWriter);
		inOrder.verify(mockWriter).write(" [R]ock, [P]aper, or [S]cissors? ");
		inOrder.verify(mockBufferedReader).readLine();
	}
}

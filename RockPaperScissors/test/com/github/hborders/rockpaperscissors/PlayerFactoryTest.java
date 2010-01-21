package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

public class PlayerFactoryTest {
	private BufferedReader mockBufferedReader;
	private Writer mockWriter;

	private PlayerFactory testObject;

	private InOrder inOrder;

	@Before
	public void setup() {
		mockBufferedReader = mock(BufferedReader.class);
		mockWriter = mock(Writer.class);

		testObject = new PlayerFactory(mockBufferedReader, mockWriter);

		inOrder = inOrder(mockBufferedReader, mockWriter);
	}

	@Test
	public void createPlayer_prints_prompt_and_trims_input_and_returns_Player_from_PlayerProvider()
			throws Exception {
		when(mockBufferedReader.readLine()).thenReturn("  foo  ").thenThrow(
				new RuntimeException("Possible infinite loop"));

		Player player = testObject.createPlayer(1);

		assertEquals(new Player("foo", 1, new WonRoundCount(0)), player);

		inOrder.verify(mockWriter).write("Player 1 Name: ");
		inOrder.verify(mockWriter).flush();
		inOrder.verify(mockBufferedReader).readLine();
	}

	@Test
	public void createPlayer_reprompts_when_input_is_null() throws Exception {
		when(mockBufferedReader.readLine()).thenReturn(null).thenReturn("bar")
				.thenThrow(new RuntimeException("Possible infinite loop"));

		Player player = testObject.createPlayer(1);

		assertEquals(new Player("bar", 1, new WonRoundCount(0)), player);

		inOrder.verify(mockWriter).write("Player 1 Name: ");
		inOrder.verify(mockWriter).flush();
		inOrder.verify(mockBufferedReader).readLine();
		inOrder.verify(mockWriter).write("Player 1 Name: ");
		inOrder.verify(mockWriter).flush();
		inOrder.verify(mockBufferedReader).readLine();
	}

	@Test
	public void createPlayer_reprompts_when_trimmed_input_is_empty()
			throws Exception {
		when(mockBufferedReader.readLine()).thenReturn("   ").thenReturn("bar")
				.thenThrow(new RuntimeException("Possible infinite loop"));

		Player player = testObject.createPlayer(1);

		assertEquals(new Player("bar", 1, new WonRoundCount(0)), player);

		inOrder.verify(mockWriter).write("Player 1 Name: ");
		inOrder.verify(mockWriter).flush();
		inOrder.verify(mockBufferedReader).readLine();
		inOrder.verify(mockWriter).write("Player 1 Name: ");
		inOrder.verify(mockWriter).flush();
		inOrder.verify(mockBufferedReader).readLine();
	}
}

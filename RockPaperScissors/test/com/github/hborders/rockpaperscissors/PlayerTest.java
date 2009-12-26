package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private WonRoundCount mockWonRoundCount;

	private Player testObject;

	@Before
	public void setup() {
		mockWonRoundCount = mock(WonRoundCount.class);

		testObject = new Player("foo", 1, mockWonRoundCount);
	}

	@Test
	public void write_writes_rawPlayer_and_playerNumber_to_Writer()
			throws Exception {
		Writer mockWriter = mock(Writer.class);

		testObject.write(mockWriter);

		verify(mockWriter).write("foo (Player 1)");
	}

	@Test
	public void getWonRoundCount() throws Exception {
		assertEquals(mockWonRoundCount, testObject.getWonRoundCount());
	}

	@Test
	public void wonRound_increments_WonRoundCount() throws Exception {
		testObject.wonRound();

		verify(mockWonRoundCount).increment();
	}
}

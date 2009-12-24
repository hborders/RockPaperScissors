package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import java.io.Writer;

import org.junit.Test;

import com.github.hborders.rockpaperscissors.Player.InvalidPlayerException;

public class PlayerTest {
	@Test(expected = InvalidPlayerException.class)
	public void constructor_throws_InvalidPlayerException_when_player_contains_no_visible_characters()
			throws Exception {
		new Player("  ", 1);
	}

	@Test(expected = InvalidPlayerException.class)
	public void constructor_throws_InvalidPlayerException_when_player_is_null()
			throws Exception {
		new Player(null, 1);
	}

	@Test
	public void write_writes_trimmed_rawPlayer_and_playerNumber_to_Writer()
			throws Exception {
		Writer mockWriter = mock(Writer.class);

		Player testObject = new Player("  foo  ", 1);
		testObject.write(mockWriter);

		verify(mockWriter).write("foo (Player 1)");
	}
}

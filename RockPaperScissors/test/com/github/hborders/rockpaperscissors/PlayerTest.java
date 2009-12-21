package com.github.hborders.rockpaperscissors;

import org.junit.Test;

import com.github.hborders.rockpaperscissors.Player.InvalidPlayerException;

public class PlayerTest {
	@Test(expected = InvalidPlayerException.class)
	public void constructor_throws_InvalidPlayerException_when_player_contains_no_visible_characters()
			throws Exception {
		new Player("  ");
	}
}

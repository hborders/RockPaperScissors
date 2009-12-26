package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

public class RockPaperScissorsIntegrationTest {
	@Test
	public void winner_wins_default_game_with_rock_against_scissors()
			throws Exception {
		InputStream defaultGameInputTxtInputStream = getClass()
				.getResourceAsStream("default-game-input.txt");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		RockPaperScissors rockPaperScissors = new RockPaperScissors(
				defaultGameInputTxtInputStream, byteArrayOutputStream);

		rockPaperScissors.play(new String[0]);

		assertEquals(-1, defaultGameInputTxtInputStream.read());

		assertThat(byteArrayOutputStream, new WinningPlayerOutputMatcher(
				"Winner (Player 1)"));
	}

	@Test
	public void winner_wins_bestof_3_with_rock_paper_scissors_against_paper_rock_paper()
			throws Exception {
		InputStream defaultGameInputTxtInputStream = getClass()
				.getResourceAsStream("bestof-3-game-input.txt");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		RockPaperScissors rockPaperScissors = new RockPaperScissors(
				defaultGameInputTxtInputStream, byteArrayOutputStream);

		rockPaperScissors.play(new String[] { "-bestof", "3" });

		assertEquals(-1, defaultGameInputTxtInputStream.read());

		assertThat(byteArrayOutputStream, new WinningPlayerOutputMatcher(
				"Winner (Player 1)"));
	}

	private static class WinningPlayerOutputMatcher extends
			BaseMatcher<ByteArrayOutputStream> {
		private final String expectedEnding;

		public WinningPlayerOutputMatcher(String expectedPlayerNameAndNumber) {
			this.expectedEnding = expectedPlayerNameAndNumber + " Wins!";
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("Expected trimmed output to end with: "
					+ expectedEnding);
		}

		@Override
		public boolean matches(Object item) {
			String trimmedOutput = new String(((ByteArrayOutputStream) item)
					.toByteArray()).trim();

			return trimmedOutput.endsWith(expectedEnding);
		}
	}
}

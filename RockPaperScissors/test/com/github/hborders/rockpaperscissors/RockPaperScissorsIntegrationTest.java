package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

public class RockPaperScissorsIntegrationTest {
	@Test
	public void winner_wins_default_game_with_rock_against_scissors()
			throws Exception {
		InputStream defaultGameInputTxtInputStream = getClass()
				.getResourceAsStream("default-game-input.txt");
		StringWriter stringWriter = new StringWriter();

		RockPaperScissors rockPaperScissors = new RockPaperScissors(
				new InputStreamReader(defaultGameInputTxtInputStream),
				stringWriter);

		rockPaperScissors.play(new String[0]);

		assertEquals(-1, defaultGameInputTxtInputStream.read());

		assertThat(stringWriter, new WinningPlayerOutputMatcher(
				"Winner (Player 1)"));
	}

	@Test
	public void winner_wins_bestof_3_with_rock_paper_scissors_against_paper_rock_paper()
			throws Exception {
		InputStream bestof3GameInputTxtInputStream = getClass()
				.getResourceAsStream("bestof-3-game-input.txt");
		StringWriter stringWriter = new StringWriter();

		RockPaperScissors rockPaperScissors = new RockPaperScissors(
				new InputStreamReader(bestof3GameInputTxtInputStream),
				stringWriter);

		rockPaperScissors.play(new String[] { "-bestof", "3" });

		assertEquals(-1, bestof3GameInputTxtInputStream.read());

		assertThat(stringWriter, new WinningPlayerOutputMatcher(
				"Winner (Player 1)"));
	}

	private static class WinningPlayerOutputMatcher extends
			BaseMatcher<StringWriter> {
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
			String trimmedOutput = ((StringWriter) item).toString().trim();

			return trimmedOutput.endsWith(expectedEnding);
		}
	}
}

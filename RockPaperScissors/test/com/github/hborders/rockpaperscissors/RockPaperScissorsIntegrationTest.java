package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

public class RockPaperScissorsIntegrationTest {
	private StringWriter stringWriter;
	private RockPaperScissors testObject;

	@Before
	public void setup() {
		stringWriter = new StringWriter();
	}

	@Test
	public void winner_wins_default_game_with_rock_against_scissors()
			throws Exception {
		InputStream defaultGameInputTxtInputStream = getClass()
				.getResourceAsStream("default-game-input.txt");

		testObject = new RockPaperScissors(new InputStreamReader(
				defaultGameInputTxtInputStream), stringWriter);

		testObject.play(new String[0]);

		assertThat(stringWriter, new WinningPlayerOutputMatcher(
				"Winner (Player 1)"));
	}

	@Test
	public void winner_wins_bestof_3_with_rock_paper_scissors_against_paper_rock_paper()
			throws Exception {
		InputStream bestof3GameInputTxtInputStream = getClass()
				.getResourceAsStream("bestof-3-game-input.txt");

		testObject = new RockPaperScissors(new InputStreamReader(
				bestof3GameInputTxtInputStream), stringWriter);

		testObject.play(new String[] { "-bestof", "3" });

		assertThat(stringWriter, new WinningPlayerOutputMatcher(
				"Winner (Player 1)"));
	}

	@Test
	public void winner_wins_to_2_with_scissors_scissors_scissors_against_rock_paper_paper()
			throws Exception {
		InputStream to2GameInputTxtInputStream = getClass()
				.getResourceAsStream("to-2-game-input.txt");

		testObject = new RockPaperScissors(new InputStreamReader(
				to2GameInputTxtInputStream), stringWriter);

		testObject.play(new String[] { "-to", "2" });

		assertThat(stringWriter, new WinningPlayerOutputMatcher(
				"Winner (Player 2)"));
	}

	@Test
	public void winner_wins_to_3_by_2_with_rock_rock_rock_paper_scissors_rock_against_paper_paper_scissors_rock_paper_scissors()
			throws Exception {
		InputStream to3By2GameInputTxtInputStream = getClass()
				.getResourceAsStream("to-3-by-2-game-input.txt");

		testObject = new RockPaperScissors(new InputStreamReader(
				to3By2GameInputTxtInputStream), stringWriter);

		testObject.play(new String[] { "-to", "3", "-by", "2" });

		assertThat(stringWriter, new WinningPlayerOutputMatcher(
				"Winner (Player 2)"));
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

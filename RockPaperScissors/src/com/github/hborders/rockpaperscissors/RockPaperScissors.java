package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;

public class RockPaperScissors {
	public static void main(String[] args) {
		RockPaperScissors rockPaperScissors = new RockPaperScissors(
				new InputStreamReader(System.in), new OutputStreamWriter(
						System.out));
		rockPaperScissors.play(args);
	}

	private final UsagePrinter usagePrinter;
	private final DefaultGameFactoryFactory defaultGameFactoryFactory;
	private final PlayerFactory playerFactory;
	private final PrintWriter printWriter;

	public RockPaperScissors(Reader reader, Writer writer) {
		this(createDefaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter(
				reader, writer));
	}

	private RockPaperScissors(
			Object[] defaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter) {
		this(
				new UsagePrinter(),
				(DefaultGameFactoryFactory) defaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter[0],
				(PlayerFactory) defaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter[1],
				(PrintWriter) defaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter[2]);
	}

	RockPaperScissors(UsagePrinter usagePrinter,
			DefaultGameFactoryFactory defaultGameFactoryFactory,
			PlayerFactory playerFactory, PrintWriter printWriter) {
		this.usagePrinter = usagePrinter;
		this.defaultGameFactoryFactory = defaultGameFactoryFactory;
		this.playerFactory = playerFactory;
		this.printWriter = printWriter;
	}

	private static Object[] createDefaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter(
			Reader reader, Writer writer) {
		BufferedReader bufferedReader = new BufferedReader(reader);
		PrintWriter printWriter = new PrintWriter(writer);
		AttemptFactory attemptFactory = new AttemptFactory();
		AttemptReader attemptReader = new AttemptReader(bufferedReader,
				printWriter, attemptFactory);

		RoundCountFactory roundCountFactory = new RoundCountFactory();
		ToByWonRoundCountFactory toByWonRoundCountFactory = new ToByWonRoundCountFactory();
		ToByGameFactoryFactory toByGameFactoryFactory = new ToByGameFactoryFactory(
				roundCountFactory, toByWonRoundCountFactory, attemptReader);

		ToWonRoundCountFactory toWonRoundCountFactory = new ToWonRoundCountFactory();
		NoOpAfterPlayHookFactory noOpAfterPlayHookFactory = new NoOpAfterPlayHookFactory();
		ToGameFactoryFactory toGameFactoryFactory = new ToGameFactoryFactory(
				roundCountFactory, toByGameFactoryFactory,
				toWonRoundCountFactory, noOpAfterPlayHookFactory, attemptReader);

		BestofWonRoundCountFactory bestofWonRoundCountFactory = new BestofWonRoundCountFactory();
		BestofGameFactoryFactory bestofGameFactoryFactory = new BestofGameFactoryFactory(
				roundCountFactory, bestofWonRoundCountFactory,
				noOpAfterPlayHookFactory, attemptReader);

		WonRoundCount defaultWinningWonRoundCount = new WonRoundCount(1);
		DefaultGameFactoryFactory defaultGameFactoryFactory = new DefaultGameFactoryFactory(
				defaultWinningWonRoundCount, noOpAfterPlayHookFactory,
				attemptReader, toGameFactoryFactory, bestofGameFactoryFactory);

		PlayerFactory playerFactory = new PlayerFactory(bufferedReader,
				printWriter);

		return new Object[] { defaultGameFactoryFactory, playerFactory,
				printWriter };
	}

	public void play(String[] args) {
		try {
			GameFactory gameFactory = defaultGameFactoryFactory
					.createGameFactory(args);
			Player firstPlayer = playerFactory.createPlayer(1);
			Player secondPlayer = playerFactory.createPlayer(2);
			Game game = gameFactory.createGame(firstPlayer, secondPlayer);
			Player winningPlayer = game.play();
			winningPlayer.write(printWriter);
			printWriter.println(" Wins!");
			printWriter.flush();
		} catch (InvalidGameArgumentsException invalidGameArgumentsException) {
			usagePrinter.printUsage();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}

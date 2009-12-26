package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class RockPaperScissors {
	public static void main(String[] args) {
		RockPaperScissors rockPaperScissors = new RockPaperScissors(System
				.console().reader(), System.console().writer());
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
		GameFactory.Provider gameFactoryProvider = new GameFactory.Provider();
		BufferedReader bufferedReader = new BufferedReader(reader);
		PrintWriter printWriter = new PrintWriter(writer);
		AttemptFactory attemptFactory = new AttemptFactory();
		AttemptReader attemptReader = new AttemptReader(bufferedReader,
				printWriter, attemptFactory);
		Round defaultRound = new Round(attemptReader, new NoOpAfterPlayHook());
		Game.Provider gameProvider = new Game.Provider();

		RoundCountFactory roundCountFactory = new RoundCountFactory();
		ToByWonRoundCountFactory toByWonRoundCountFactory = new ToByWonRoundCountFactory();
		Round.Provider roundProvider = new Round.Provider();
		ToByAfterPlayHook.Provider toByAfterPlayHookProvider = new ToByAfterPlayHook.Provider();
		ToByGameFactoryFactory toByGameFactoryFactory = new ToByGameFactoryFactory(
				roundCountFactory, toByWonRoundCountFactory, roundProvider,
				attemptReader, toByAfterPlayHookProvider, gameFactoryProvider,
				gameProvider);

		ToWonRoundCountFactory toWonRoundCountFactory = new ToWonRoundCountFactory();
		ToGameFactoryFactory toGameFactoryFactory = new ToGameFactoryFactory(
				roundCountFactory, toWonRoundCountFactory,
				toByGameFactoryFactory, gameProvider, defaultRound,
				gameFactoryProvider);

		BestofWonRoundCountFactory bestofWonRoundCountFactory = new BestofWonRoundCountFactory();
		BestofGameFactoryFactory bestofGameFactoryFactory = new BestofGameFactoryFactory(
				roundCountFactory, bestofWonRoundCountFactory,
				gameFactoryProvider, gameProvider, defaultRound);

		WonRoundCount defaultWinningWonRoundCount = new WonRoundCount(1);
		DefaultGameFactoryFactory defaultGameFactoryFactory = new DefaultGameFactoryFactory(
				gameFactoryProvider, defaultRound, defaultWinningWonRoundCount,
				gameProvider, toGameFactoryFactory, bestofGameFactoryFactory);

		WonRoundCount.Provider wonRoundCountProvider = new WonRoundCount.Provider();
		Player.Provider playerProvider = new Player.Provider();
		PlayerFactory playerFactory = new PlayerFactory(bufferedReader,
				printWriter, wonRoundCountProvider, playerProvider);

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

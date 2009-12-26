package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class RockPaperScissors {
	public static void main(String[] args) {
		RockPaperScissors rockPaperScissors = new RockPaperScissors(System.in,
				System.out);
		rockPaperScissors.play(args);
	}

	private final UsagePrinter usagePrinter;
	private final DefaultGameFactoryFactory defaultGameFactoryFactory;
	private final PlayerFactory playerFactory;
	private final PrintWriter printWriter;

	public RockPaperScissors(InputStream inputStream, OutputStream outputStream) {
		this(createDefaultGameFactoryFactoryAndPlayerFactoryAndPrintWriter(
				inputStream, outputStream));
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
			InputStream inputStream, OutputStream outputStream) {
		GameFactory.Provider gameFactoryProvider = new GameFactory.Provider();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		PrintWriter printWriter = new PrintWriter(outputStream);
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

package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class RockPaperScissors {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new StandardCommandLineModule());
		RockPaperScissors rockPaperScissors = injector
				.getInstance(RockPaperScissors.class);

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

	@Inject
	public RockPaperScissors(UsagePrinter usagePrinter,
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
		Game.Provider gameProvider = new Game.Provider();

		RoundCountFactory roundCountFactory = new RoundCountFactory();
		WonRoundCount.Provider wonRoundCountProvider = new WonRoundCount.Provider();
		ToByWonRoundCountFactory toByWonRoundCountFactory = new ToByWonRoundCountFactory(
				wonRoundCountProvider);
		ToByAfterPlayHookFactory.Provider toByAfterPlayHookFactoryProvider = new ToByAfterPlayHookFactory.Provider();
		ToByAfterPlayHook.Provider toByAfterPlayHookProvider = new ToByAfterPlayHook.Provider();
		Round.Provider roundProvider = new Round.Provider();
		ToByGameFactoryFactory toByGameFactoryFactory = new ToByGameFactoryFactory(
				roundCountFactory, toByWonRoundCountFactory,
				toByAfterPlayHookFactoryProvider, toByAfterPlayHookProvider,
				roundProvider, attemptReader, gameFactoryProvider, gameProvider);

		ToWonRoundCountFactory toWonRoundCountFactory = new ToWonRoundCountFactory();
		NoOpAfterPlayHookFactory noOpAfterPlayHookFactory = new NoOpAfterPlayHookFactory();
		ToGameFactoryFactory toGameFactoryFactory = new ToGameFactoryFactory(
				roundCountFactory, toByGameFactoryFactory,
				toWonRoundCountFactory, gameFactoryProvider,
				noOpAfterPlayHookFactory, attemptReader, roundProvider,
				gameProvider);

		BestofWonRoundCountFactory bestofWonRoundCountFactory = new BestofWonRoundCountFactory();
		BestofGameFactoryFactory bestofGameFactoryFactory = new BestofGameFactoryFactory(
				roundCountFactory, bestofWonRoundCountFactory,
				gameFactoryProvider, noOpAfterPlayHookFactory, attemptReader,
				roundProvider, gameProvider);

		WonRoundCount defaultWinningWonRoundCount = new WonRoundCount(1);
		DefaultGameFactoryFactory defaultGameFactoryFactory = new DefaultGameFactoryFactory(
				gameFactoryProvider, defaultWinningWonRoundCount,
				noOpAfterPlayHookFactory, attemptReader, roundProvider,
				gameProvider, toGameFactoryFactory, bestofGameFactoryFactory);

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

package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import com.google.inject.AbstractModule;

public class StandardCommandLineModule extends AbstractModule {
	@Override
	protected void configure() {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				System.out);
		bind(PrintWriter.class).toInstance(
				new PrintWriter(outputStreamWriter));
		bind(Writer.class).toInstance(outputStreamWriter);
		bind(BufferedReader.class).toInstance(
				new BufferedReader(new InputStreamReader(System.in)));
		bind(WonRoundCount.class).toInstance(
				new WonRoundCount.Provider().provide(1));
	}
}
package com.github.hborders.rockpaperscissors;

import java.io.Writer;

public class Console {
	public String readLine(String fmt, Object... args) {
		return System.console().readLine(fmt, args);
	}

	public Writer writer() {
		return System.console().writer();
	}
}

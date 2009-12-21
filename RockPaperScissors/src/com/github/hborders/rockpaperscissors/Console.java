package com.github.hborders.rockpaperscissors;

public class Console {
	public String readLine(String fmt, Object... args) {
		return System.console().readLine(fmt, args);
	}
}

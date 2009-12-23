package com.github.hborders.rockpaperscissors;

public class AbstractGame {

	static class Provider {
		public AbstractGame provide() {
			return new AbstractGame();
		}
	}
}

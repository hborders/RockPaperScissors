package com.github.hborders.rockpaperscissors;

public class BestofGame extends AbstractGame {

	static class Provider {
		public BestofGame provide() {
			return new BestofGame();
		}
	}
}

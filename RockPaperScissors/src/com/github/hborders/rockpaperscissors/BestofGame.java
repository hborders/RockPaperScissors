package com.github.hborders.rockpaperscissors;

public class BestofGame implements IGame {

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	static class Provider {
		public BestofGame provide() {
			return new BestofGame();
		}
	}
}

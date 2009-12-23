package com.github.hborders.rockpaperscissors;

public interface IGameFactory {
	IGame createGame(Player firstPlayer, Player secondPlayer);
}

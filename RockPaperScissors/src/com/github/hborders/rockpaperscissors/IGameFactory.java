package com.github.hborders.rockpaperscissors;

public interface IGameFactory {
	AbstractGame createGame(Player firstPlayer, Player secondPlayer);
}

package com.gamechallenge.app.serviceImpl;

import org.springframework.stereotype.Service;

import com.gamechallenge.app.model.GameChart;
import com.gamechallenge.app.model.GamePlay;
import com.gamechallenge.app.model.Player;
import com.gamechallenge.app.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	private GameChart gameChart;

	public GameServiceImpl(GameChart gameChart) {
		super();
		this.gameChart = gameChart;
		this.init();
	}

	@Override
	public GameChart placePawn(GamePlay p) {
		gameChart.placePawn(p);
		return gameChart;
	}

	@Override
	public GameChart resetGame() {
		gameChart.init();
		return gameChart;
	}

	@Override
	public boolean registerPlayer(Player p) {
		return gameChart.registerPlayer(p);
	}

	private void init() {
		gameChart.init();
	}

}

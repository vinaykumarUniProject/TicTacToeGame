package com.gamechallenge.app.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * This class contains the game chart (a 2d array), players,
 * status of the game. This schema can be used by the UI components.
 * 
 */

@Component
@Data
public class GameChart {

	private int[][] gameChart;

	private List<Player> players = new ArrayList<Player>();

	private String gameStatus = "Game is ON!";

	private String togglePlayer;

	private int currentStep = 0;

	public GameChart() {
	}

	public void init() {
		gameChart = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameChart[i][j] = 0;
			}
		}
		players = null;
		togglePlayer = null;
		players = new ArrayList<Player>();
		gameStatus = "Game is ON!";
	}

	public void placePawn(GamePlay p) {

		if (canContinueToPlay(p)) {
			currentStep = currentStep + 1;
			if (players.get(0).getName().equals(p.getPlayerName())) {
				gameChart[p.getPosX()][p.getPosY()] = 1;
				if (checkPlayerStatus(gameChart, GameChartConstants.PLAYER_ONE_WIN_RESULT)) {
					gameStatus = "Game Finished " + players.get(0).getName() + " Won the game!!";
				}
			} else {
				gameChart[p.getPosX()][p.getPosY()] = 2;
				if (checkPlayerStatus(gameChart, GameChartConstants.PLAYER_TWO_WIN_RESULT)) {
					gameStatus = "Game Finished " + players.get(1).getName() + " Won the game!!";
				}
			}

			if (!gameStatus.contains("Game Finished ")) {
				if (currentStep == GameChartConstants.TOTAL_NUMBER_OF_STEPS) {
					gameStatus = "Game Draw!!";
				}
			}
		}
	}

	private boolean canContinueToPlay(GamePlay p) {
		boolean continuePlay = false;
		if (gameStatus.contains("Game Finished") || gameStatus.contains("Game Draw")) {
			gameStatus = "Error! Please reset the game to play again";
		} else {
			if (players.size() == 2) {
				if (players.get(0).getName().equals(p.getPlayerName())
						|| players.get(1).getName().equals(p.getPlayerName())) {
					if (!p.getPlayerName().equals(togglePlayer)) {

						if (gameChart[p.getPosX()][p.getPosY()] == 0) {
							togglePlayer = p.getPlayerName();
							continuePlay = true;
							gameStatus = "Game is ON!";
						} else {
							gameStatus = "Error!! Pawn is already in place";
						}

					} else {
						gameStatus = "Error!! The other player should play!!";
					}
				} else {
					gameStatus = "Error!! The given player name is not registered!!";
				}
			} else {
				gameStatus = "Error!! Please register 2 players";
			}

		}

		return continuePlay;
	}

	public boolean registerPlayer(Player p) {
		boolean addedPlayer = false;
		if (!players.contains(p) && players.size() < 2) {
			players.add(p);
			if (players.size() == 2) {
				togglePlayer = players.get(1).getName();
			}
			addedPlayer = true;
		}
		return addedPlayer;
	}

	private boolean checkPlayerStatus(int[][] arr, int playerResult) {
		boolean resPlayerRow = false;
		boolean resPlayerCol = false;
		boolean resPlayerMajDiag = false;
		boolean resPlayerMinDiag = false;

		for (int row = 0; row < 3; row++) {
			int sumRow = 0;
			if (!resPlayerRow) {
				for (int col = 0; col < 3; col++) {
					if (arr[row][col] != 0) {
						sumRow = sumRow + arr[row][col];
						if (sumRow == playerResult && col == 2) {
							resPlayerRow = true;
							break;
						}
					} else {
						col = 3;
					}
				}
			} else {
				break;
			}
		}

		for (int col = 0; col < 3; col++) {
			int sumCol = 0;
			if (!resPlayerCol) {
				for (int row = 0; row < 3; row++) {
					if (arr[row][col] != 0) {
						sumCol = sumCol + arr[row][col];
						if (sumCol == playerResult && row == 2) {
							resPlayerCol = true;
							break;
						}
					} else {
						row = 3;
					}
				}
			} else {
				break;
			}
		}
		int sumMajDiag = 0;
		for (int row = 0; row < 3; row++) {
			if (arr[row][row] != 0) {
				sumMajDiag = arr[row][row] + sumMajDiag;
				if (sumMajDiag == playerResult && row == 2) {
					resPlayerMajDiag = true;
				}
			} else {
				row = 3;
			}
		}

		int sumMinDiag = 0;
		for (int row = 0; row < 3; row++) {
			if (arr[row][2 - row] != 0) {
				sumMinDiag = arr[row][2 - row] + sumMinDiag;
				if (sumMinDiag == playerResult && (2 - row) == 0) {
					resPlayerMinDiag = true;
				}
			} else {
				row = 3;
			}
		}

		return (resPlayerRow || resPlayerCol || resPlayerMajDiag || resPlayerMinDiag);
	}

}

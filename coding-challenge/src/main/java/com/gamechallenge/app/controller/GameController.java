package com.gamechallenge.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamechallenge.app.model.GameChart;
import com.gamechallenge.app.model.GamePlay;
import com.gamechallenge.app.model.Player;
import com.gamechallenge.app.service.GameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Game Controller APIs", tags = { "Game Controller APIs" })
public class GameController {

	private GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping(value = "/placePawn", produces = "application/json")
	@ApiOperation(value = "place a pawn on the game chart", response = GameChart.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully placed the pawn"),
			@ApiResponse(code = 400, message = "Bad request- reason description in the \"gameStatus\" string in the response json "), })
	public ResponseEntity<GameChart> placePawn(@RequestBody GamePlay gamePlay) {
		GameChart gc = gameService.placePawn(gamePlay);
		if (gc.getGameStatus().contains("Error")) {
			return new ResponseEntity<GameChart>(gc, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<GameChart>(gc, HttpStatus.OK);
		}

	}

	@PostMapping(value = "/registerPlayer", produces = "application/json")
	@ApiOperation(value = "Register a player", response = Player.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully registered the player"),
			@ApiResponse(code = 400, message = "Bad request when same player is registered or more than two players are registered"), })
	public ResponseEntity<Player> registerPlayer(@RequestBody Player player) {
		if (gameService.registerPlayer(player)) {
			return new ResponseEntity<Player>(player, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Player>(player, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/resetGame", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully reset the game"), })
	public ResponseEntity<GameChart> resetGame() {
		return new ResponseEntity<GameChart>(gameService.resetGame(), HttpStatus.OK);
	}

}

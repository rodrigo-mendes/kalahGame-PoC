package com.rms.kalah.primaryAdapter.movement.dto;

import com.rms.kalah.domain.GameBoard;
import com.rms.kalah.primaryAdapter.game.dto.GameDTO;

import java.util.Objects;

public class BoardStatusDTO {
    private GameDTO gameDTO;
    private GameBoard gameBoard;

    public BoardStatusDTO() {
    }

    public BoardStatusDTO(GameDTO gameDTO, GameBoard gameBoard) {
        this.gameDTO = gameDTO;
        this.gameBoard = gameBoard;
    }

    public BoardStatusDTO(Integer gameId, String servername, String port, GameBoard gameBoard){
        this.gameDTO = new GameDTO(gameId,servername, port);
        this.gameBoard = gameBoard;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardStatusDTO that = (BoardStatusDTO) o;
        return gameDTO.equals(that.gameDTO) &&
                gameBoard.equals(that.gameBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameDTO, gameBoard);
    }

    @Override
    public String toString() {
        return "BoardStatusDTO{" +
                "gameDTO=" + gameDTO +
                ", gameBoard=" + gameBoard +
                '}';
    }
}

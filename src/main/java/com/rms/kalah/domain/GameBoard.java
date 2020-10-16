package com.rms.kalah.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class GameBoard {
    
    private Map<Integer, Pit> board;
    private GameStatus status;
    private transient int turn = 1;

    public int getTurn() {
        return turn;
    }

    public void makeMove(){
        turn++;
    }

    public GameBoard(){
        this(6);
    }
    
    public GameBoard(int stones){
        board = new HashMap<>();
        status = GameStatus.OPENED;
        IntStream.range(1,15).forEach( i -> board.put(i, new Pit(i, stones)));
        board.put(7, new Pit(7, 0));
        board.put(14, new Pit(14, 0));
    }

    public void setBoard(Map<Integer, Pit> board) {
        this.board = board;
    }

    public Map<Integer, Pit> getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameBoard gameBoard = (GameBoard) o;

        if (getTurn() != gameBoard.getTurn()) return false;
        if (!getBoard().equals(gameBoard.getBoard())) return false;
        return getStatus() == gameBoard.getStatus();
    }

    @Override
    public int hashCode() {
        int result = getBoard().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getTurn();
        return result;
    }

    @Override
    public String toString() {
        return "GameBoard{" +
                "board=" + board +
                ", status=" + status +
                ", turn=" + turn +
                '}';
    }
}

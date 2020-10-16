package com.rms.kalah.secondaryAdapter;

import com.rms.kalah.domain.GameBoard;

public interface GameStatusRepository {
    Integer createGame();
    GameBoard retrieveGame(Integer gameId);
    void updateGame(Integer gameId, GameBoard gameBoard);
}

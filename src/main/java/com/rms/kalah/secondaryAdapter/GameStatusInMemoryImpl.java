package com.rms.kalah.secondaryAdapter;

import com.rms.kalah.domain.GameBoard;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameStatusInMemoryImpl implements GameStatusRepository {

    private transient Map<Integer, GameBoard> games = new HashMap<>();
    private transient Integer lastGameId = 0;

    @Override
    public Integer createGame(){
        lastGameId++;
        games.put(lastGameId, new GameBoard());
        return lastGameId;
    }

    @Override
    public GameBoard retrieveGame(Integer gameId){
        return games.get(gameId);
    }

    @Override
    public void updateGame(Integer gameId, GameBoard gameBoard){
        games.put(lastGameId, gameBoard);
    }

}

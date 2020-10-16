package com.rms.kalah.rules;

import com.rms.kalah.domain.GameBoard;
import com.rms.kalah.domain.GameStatus;
import com.rms.kalah.domain.Pit;
import com.rms.kalah.error.exception.InvalidGameIdException;
import com.rms.kalah.error.exception.IsKalahPitException;
import com.rms.kalah.error.exception.PitEmptyException;
import com.rms.kalah.error.exception.PitOutOfRangeException;
import com.rms.kalah.secondaryAdapter.GameStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KalahGameMotorImpl implements KalahGameMotor {

    public static final int MINIMUN_STONES = 1;
    public static final int KALAH_P2 = 14;
    public static final int KALAH_P1 = 7;

    @Autowired
    private transient GameStatusRepository repo;

    @Override
    public GameBoard makeMovement(Integer gameId, Integer pitId) throws PitOutOfRangeException, IsKalahPitException, PitEmptyException, InvalidGameIdException{
        validatePitId(pitId);

        GameBoard gameBoard = repo.retrieveGame(gameId);
        validateGameBoard(gameBoard);

        ArrayList<Pit> collect = gameBoard.getBoard().values()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));

        int stones = collect.get(pitId-1).getStoneNumbers();

        validateStonesNumber(stones);
        //get Stones
        collect.get(pitId-1).setStoneNumbers(0);
        //remove Kalah from adversary
        collect.remove((gameBoard.getTurn() % 2) * 7);

        // create a list of nexts pits
        List<Pit> pits = collect.subList(pitId-1, collect.size());

        if(pits.size() < stones){
            pits.addAll(collect.subList(0, stones - pits.size() ) ) ;
        }else{
            pits = collect.subList(1, stones+1);
        }

        pits.forEach( (pit) -> pit.setStoneNumbers(pit.getStoneNumbers()+1));

        Pit lastPit = pits.get(pits.size() - 1);

        List pitWithStones = collect.stream().filter( p -> !p.isEmpty() ).filter( p -> (p.getId() != 7 || p.getId() != 14 ) ).collect(Collectors.toList());
        if( lastPit.getStoneNumbers() > MINIMUN_STONES){
            int lastId = chooseNextPit(pitWithStones);
            gameBoard = makeMovement(gameId, lastId);
        }else{
            if (pitWithStones.isEmpty()){
                gameBoard.setStatus(GameStatus.FINISHED);
            }
        }

        repo.updateGame(gameId, gameBoard);

        return gameBoard;
    }

    private int chooseNextPit(List<Pit> lastPit) {
        return lastPit.get(0).getId();
    }

    private void validateGameBoard(GameBoard gameBoard) throws InvalidGameIdException {
        if(gameBoard == null){
            throw new InvalidGameIdException("Invalid GameId");
        }
    }

    private void validateStonesNumber(int stones) throws PitEmptyException {
        if (stones == 0) {
            throw new PitEmptyException("Pit Empty");
        }
    }

    private void validatePitId(Integer pitId) throws PitOutOfRangeException, IsKalahPitException {

        switch ( pitId ){ // java 14
            case KALAH_P1, KALAH_P2 -> throw new IsKalahPitException("Pit is a Kalah") ;
        }

        if(pitId > KALAH_P2){
            //Exception this pit is invalid
            throw new PitOutOfRangeException("Pit out of Range");
        }
    }

    @Override
    public Integer createGame() {
        return repo.createGame();
    }
}

package com.rms.kalah.rules;

import com.rms.kalah.domain.GameBoard;
import com.rms.kalah.error.exception.InvalidGameIdException;
import com.rms.kalah.error.exception.IsKalahPitException;
import com.rms.kalah.error.exception.PitEmptyException;
import com.rms.kalah.error.exception.PitOutOfRangeException;

public interface KalahGameMotor {
    GameBoard makeMovement(Integer gameId, Integer pitId) throws PitOutOfRangeException, IsKalahPitException, PitEmptyException, InvalidGameIdException;

    Integer createGame();
}

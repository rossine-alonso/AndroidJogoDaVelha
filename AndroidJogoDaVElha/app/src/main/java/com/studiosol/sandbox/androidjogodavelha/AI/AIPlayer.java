package com.studiosol.sandbox.androidjogodavelha.AI;


/**
 * Created by Rossine on 5/11/15.
 */
public abstract class AIPlayer {

    /**
     *
     * @param board
     * integer array where:
     * 0 means empty slot
     * 1 means opponent slots
     * 2 means my(the robot) slots
     */

    public abstract Choice Move(int[][] board);
}

package com.studiosol.sandbox.androidjogodavelha.AI;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rossine on 5/11/15.
 * This AI randomly choose the empty spaces.
 */
public class AIPlayer1 extends AIPlayer {
    @Override
    public Choice Move(int[][] board) {
        //look for the empty spaces
        ArrayList<Choice> emptySlots = getEmptySlots(board);
        Choice chosenSlot;
        Random rand = new Random();

        //chooses one
        switch(emptySlots.size()){
            case 0:
                return null;
            case 1:
                chosenSlot = emptySlots.get(0);
                break;
            default:
                chosenSlot = emptySlots.get(rand.nextInt(emptySlots.size()));
                break;
        }

        return chosenSlot;

    }

    private ArrayList<Choice> getEmptySlots(int[][] board){
        ArrayList<Choice> emptySlots = new ArrayList<Choice>();
        int size = board.length;

        for(int i = 0; i < size; ++i ){
            for(int j = 0; j < size; ++j){
                if(board[i][j] == 0){
                    emptySlots.add(new Choice(i,j));
                }
            }
        }

        return emptySlots;
    }
}

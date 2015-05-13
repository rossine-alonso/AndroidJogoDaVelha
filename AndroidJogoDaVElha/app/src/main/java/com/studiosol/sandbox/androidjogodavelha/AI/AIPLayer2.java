package com.studiosol.sandbox.androidjogodavelha.AI;

import java.util.ArrayList;

/**
 * Created by Rossine on 5/12/15.
 */
public class AIPLayer2 extends AIPlayer{
    @Override
    public Choice Move(int[][] board) {
//        int position;
//        int turn;
//
//        ArrayList<Choice> emptySlots = getEmptySlots(board);
//        turn = 5 - emptySlots.size()/2;
//
//        if(turn < 3) {
//            for (Choice c : emptySlots) {
//                position = c.getLine() * 3 + c.getColumn();
//                if (position % 2 == 0 && position != 5) {
//                    return c;
//                }
//            }
//        }else if(turn < 4){
//            for (Choice c : emptySlots) {
//                position = c.getLine() * 3 + c.getColumn();
//                if (position % 2 == 0) {
//                    return c;
//                }
//            }
//        }else{
//            int count;
//            for (int i = 0; i < board.length; ++i ) {
//                for(int j = 0; j < board.length; ++j){
//
//                }
//                position = c.getLine() * 3 + c.getColumn();
//                if (position % 2 == 0) {
//                    return c;
//                }
//            }
//
//        }

        return null;
    }

    private Choice tryToWin(int[][] board){
return null;
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

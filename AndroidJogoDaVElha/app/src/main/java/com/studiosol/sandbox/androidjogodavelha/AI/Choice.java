package com.studiosol.sandbox.androidjogodavelha.AI;

/**
 * Created by Rossine on 5/12/15.
 */
public class Choice {
    private int line = 0;
    private int column = 0;

    public Choice(int l, int c){
        line = l;
        column = c;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}

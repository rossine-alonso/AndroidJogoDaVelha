package com.studiosol.sandbox.androidjogodavelha.Backend;

/**
 * Created by Rossine on 5/8/15.
 */
public class Slot {


    // X , O or V (vazio)
    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty(){
        return symbol == 'V';

    }






}

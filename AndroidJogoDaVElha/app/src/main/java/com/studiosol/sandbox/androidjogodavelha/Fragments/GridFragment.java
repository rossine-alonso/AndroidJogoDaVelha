package com.studiosol.sandbox.androidjogodavelha.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.studiosol.sandbox.androidjogodavelha.AI.AIPlayer;
import com.studiosol.sandbox.androidjogodavelha.AI.AIPlayer1;
import com.studiosol.sandbox.androidjogodavelha.AI.Choice;
import com.studiosol.sandbox.androidjogodavelha.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSlotClickListener} interface
 * to handle interaction events.
 * Use the {@link GridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridFragment extends Fragment implements View.OnClickListener{
    private boolean isX = true;
    private int turn = 1;
    private boolean isGridLocked = false;

    //Slots
    private static final ImageButton[] SLOTS = new ImageButton[9];
    private static int[] state_slots = {0,0,0,0,0,0,0,0,0};

    //machine is O
    private static final boolean OPPONENT_IS_MACHINE = true;
    private static final int MACHINE_PLAYER = 0;
    private AIPlayer robot = new AIPlayer1();


    private static final String STATE_TEXT = "current_text";
    private static final String STATE_GRID = "grid_state";
    private static final String STATE_LOCKED = "locked_state";


    private OnSlotClickListener mListener;

    public static GridFragment newInstance(OnSlotClickListener mListener) {
        GridFragment fragment = new GridFragment();
        fragment.mListener = mListener;
        return fragment;
    }

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_2, container, false);


        SLOTS[0] = (ImageButton) view.findViewById(R.id.b0);
        SLOTS[1] = (ImageButton) view.findViewById(R.id.b1);
        SLOTS[2] = (ImageButton) view.findViewById(R.id.b2);

        SLOTS[3] = (ImageButton) view.findViewById(R.id.b3);
        SLOTS[4] = (ImageButton) view.findViewById(R.id.b4);
        SLOTS[5] = (ImageButton) view.findViewById(R.id.b5);

        SLOTS[6] = (ImageButton) view.findViewById(R.id.b6);
        SLOTS[7] = (ImageButton) view.findViewById(R.id.b7);
        SLOTS[8] = (ImageButton) view.findViewById(R.id.b8);

        for(ImageButton ib : SLOTS){
            ib.setOnClickListener(this);
        }

        return view;
    }


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnSlotClickListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnSlotClickListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView text = (TextView) getView().findViewById(R.id.info_bar);

        outState.putIntArray(STATE_GRID, state_slots);
        outState.putBoolean(STATE_LOCKED, isGridLocked);
        outState.putCharSequence(STATE_TEXT, text.getText());

        super.onSaveInstanceState(outState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            TextView text = (TextView) getView().findViewById(R.id.info_bar);

            text.setText(savedInstanceState.getCharSequence(STATE_TEXT));
            setGrid(savedInstanceState.getIntArray(STATE_GRID));
            if(savedInstanceState.getBoolean(STATE_LOCKED)){
                lockAllSlots();
            }

        }
    }

    public void setGrid(int[] grid){
        for(int i = 0; i < grid.length; ++i){
            state_slots[i] = grid[i];
            switch(grid[i]){
                case 0:
                    SLOTS[i].setImageResource(R.drawable.slot);
                    break;
                case 1:
                    SLOTS[i].setImageResource(R.drawable.ex);
                    break;
                case 2:
                    SLOTS[i].setImageResource(R.drawable.ring);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        CharSequence tag;
        int position;
        int player;

        ImageButton ibutton = (ImageButton) v;

        ibutton.setClickable(false);
        tag = (CharSequence) ibutton.getTag();
        position = (int) tag.charAt(0) - 48;


        if(isX){
            ibutton.setImageResource(R.drawable.ex);
            player = 1;
        }else{
            ibutton.setImageResource(R.drawable.ring);
            player = 2;
        }
        state_slots[position] = player;
        isX = !isX;

        if (didWon(player)) {
            endGame(player);
            lockAllSlots();
            return;
        }

        ++turn;

        if (turn > 9) { //draw game
            endGame(0);
            lockAllSlots();
            return;
        } else {
            mListener.onSlotClick(position, turn);
        }

        if (OPPONENT_IS_MACHINE && turn % 2 == MACHINE_PLAYER) {
            int[][] AIBoard = getAIBoard();

            //call machine move
            Choice choice = robot.Move(AIBoard);

            if(choice != null) {
                //click on the slot
                onClick(SLOTS[choice.getLine() * 3 + choice.getColumn()]);
            }
        }
    }

    private boolean didWon(int player) {

        int i, j;
        int size = (int) Math.sqrt(state_slots.length);

        //horizontal
        for (i = 0; i < size; ++i) {
            for (j = 0; j < size; ++j) {
                if (state_slots[i*3 + j] != player) {
                    break;
                }
            }
            if (j == 3) {
                return true;
            }
        }

        //vertical
        for (j = 0; j < size; ++j) {
            for (i = 0; i < size; ++i) {
                if (state_slots[i*3 + j] != player) {
                    break;
                }
            }
            if (i == 3) {
                return true;
            }
        }

        //diagonal 1
        for (i = 0, j = 2; i < size; ++i, --j) {
            if (state_slots[i*3 + j] != player) {
                break;
            }
        }
        if (i == 3) {
            return true;
        }

        //diagonal 2
        for (i = 0, j = 0; i < size; ++i, ++j) {
            if (state_slots[i*3 + j] != player) {
                break;
            }
        }
        if (i == 3) {
            return true;
        }

        return false;
    }

    private int[][] getAIBoard() {
        int size = (int) Math.sqrt(state_slots.length);
        int[][] AIBoard = new int[size][size];

        for (int i = 0; i < state_slots.length; ++i) {
            AIBoard[i/3][i%3] = state_slots[i];
        }

        return AIBoard;
    }

    public void lockAllSlots(){
        setAllSlotsClickable(false);
    }

    private void unlockAllSlots(){
        setAllSlotsClickable(true);
    }

    public void resetSlots(){

        for(int i= 0; i < SLOTS.length; ++i ){
            SLOTS[i].setImageResource(R.drawable.slot);
            state_slots[i] = 0;
        }
        turn = 1;

        unlockAllSlots();

        isX = true;

        TextView text = (TextView) getView().findViewById(R.id.info_bar);
        text.setText(R.string.player_turn);
    }

    private void setAllSlotsClickable(boolean state){
        for(ImageButton ib: SLOTS){
            ib.setClickable(state);
        }
        isGridLocked = !state;
    }

    private void endGame(int player){
        TextView text = (TextView) getView().findViewById(R.id.info_bar);
        switch (player) {
            case 0:
                text.setText(R.string.draw_game);
                break;
            case 1:
                text.setText(R.string.you_win);
                break;
            case 2:
                text.setText(R.string.you_lose);
                break;
        }
    }

    public void setListener(OnSlotClickListener mListener) {
        this.mListener = mListener;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSlotClickListener {
        public void onSlotClick(int position, int turn);
        public void endGame(int player);
    }

}

package com.studiosol.sandbox.androidjogodavelha.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.studiosol.sandbox.androidjogodavelha.AI.AIPlayer;
import com.studiosol.sandbox.androidjogodavelha.AI.AIPlayer1;
import com.studiosol.sandbox.androidjogodavelha.Fragments.GridFragment;
import com.studiosol.sandbox.androidjogodavelha.Fragments.GridFragment.OnSlotClickListener;
import com.studiosol.sandbox.androidjogodavelha.R;


public class MainActivity extends AppCompatActivity implements OnSlotClickListener {

    private static final String STATE_TEXT = "current_text";
    private static final String STATE_TITLE = "current_title";

    //machine is O
    private boolean opponentIsMachine = true;
    private int machinePlayer = 0;
    private AIPlayer robot = new AIPlayer1();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_reset:
                resetGame();
                return true;

            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView text = (TextView) findViewById(R.id.info_bar);

        outState.putCharSequence(STATE_TEXT, text.getText());
        outState.putCharSequence(STATE_TITLE, getTitle());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.info_bar);

        text.setText(savedInstanceState.getCharSequence(STATE_TEXT));
        setTitle(savedInstanceState.getCharSequence(STATE_TITLE));
    }

    @Override
    public void onSlotClick(int position, int turn) {
        //deal with the move. In this case ignore position
        setTitle("Rodada " + (turn + 1) / 2);
    }

    @Override
    public void endGame(int player) {
        TextView text = (TextView) findViewById(R.id.info_bar);
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

    void resetGame(){
        GridFragment gridFrag = (GridFragment) getFragmentManager().findFragmentById(R.id.grid_fragment);

        gridFrag.resetSlots();
    }
}

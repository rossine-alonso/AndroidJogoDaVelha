package com.studiosol.sandbox.androidjogodavelha.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.studiosol.sandbox.androidjogodavelha.Fragments.GridFragment;
import com.studiosol.sandbox.androidjogodavelha.Fragments.GridFragment.OnSlotClickListener;
import com.studiosol.sandbox.androidjogodavelha.R;


public class MainActivity extends AppCompatActivity implements OnSlotClickListener {

    private static final String STATE_TITLE = "current_title";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            setTitle("Rodada 1");
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            GridFragment frag = GridFragment.newInstance(this);
            fragmentTransaction.add(R.id.fragment_container, frag);
            fragmentTransaction.commit();
        }
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
        outState.putCharSequence(STATE_TITLE, getTitle());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle(savedInstanceState.getCharSequence(STATE_TITLE));

        GridFragment gridFrag = (GridFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        if(gridFrag != null) {
            gridFrag.setListener(this);
        }

    }

    @Override
    public void onSlotClick(int position, int turn) {
        //deal with the move. In this case ignore position
        setTitle("Rodada " + (turn + 1) / 2);
    }

    @Override
    public void endGame(int player) {

    }


    void resetGame(){
        GridFragment gridFrag = (GridFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        if(gridFrag != null) {
            gridFrag.resetSlots();
        }

        setTitle("Rodada 1");
    }
}

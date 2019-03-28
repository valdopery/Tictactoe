package com.valdo.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

      // flag=0 yellow, flag=1 black;
       int flag=0;
    // 2 means unplayed
     int gameState[]={2,2,2,2,2,2,2,2,2};
     int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive=true;

      public void dropin(View view) {

          ImageView counter = (ImageView) view;

          int tappedCounter = Integer.parseInt(counter.getTag().toString());

          if (gameState[tappedCounter] == 2 && gameIsActive) {
               gameState[tappedCounter]=flag;
              counter.setTranslationY(-1000f);
              if (flag == 0) {
                  counter.setImageResource(R.drawable.yl);
                  flag = 1;
              } else {
                  counter.setImageResource(R.drawable.bola);
                  flag = 0;
              }

              counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
              for(int[] winningPosition : winningPositions){
                  if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                          gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                          gameState[winningPosition[0]] !=2){

                      gameIsActive=false;

                      String winner="Black";

                   if(gameState[winningPosition[0]]==0)
                       winner="Yellow";

                      // Winner message

                      TextView txt=(TextView)findViewById(R.id.wonMessage) ;
                      txt.setText(winner+" has won!");
                      LinearLayout lt=(LinearLayout)findViewById(R.id.againLayout);
                      lt.setVisibility(View.VISIBLE);
                  }else
                  {
                      boolean gameIsover=true;
                      for(int counterState: gameState){
                          if(counterState==2)
                              gameIsover=false;
                      }
                      if(gameIsover){
                          TextView txt=(TextView)findViewById(R.id.wonMessage) ;
                          txt.setText("It is a draw");
                          LinearLayout lt=(LinearLayout)findViewById(R.id.againLayout);
                          lt.setVisibility(View.VISIBLE);
                      }
                  }
              }
          }

      }



    public void Denovo(View view){
        gameIsActive=true;
        LinearLayout lt=(LinearLayout)findViewById(R.id.againLayout);
        lt.setVisibility(View.INVISIBLE);
        // flag=0 yellow, flag=1 black;
         flag=0;
        // 2 means unplayed
         for(int i=0;i<gameState.length;i++){
             gameState[i]=2;
         }

        GridLayout grd=(GridLayout)findViewById(R.id.gridV);
        for(int i=0;i<grd.getChildCount();i++){
            ((ImageView)grd.getChildAt(i)).setImageResource(0);
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

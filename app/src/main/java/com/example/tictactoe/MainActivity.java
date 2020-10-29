package com.example.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b0, b1,b2,b3,b4,b5,b6,b7,b8;
    TextView headerText;
    int P_o = 0;
    int P_x = 1;
    int activeP = P_o;
    int[] filledP = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean activeG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = (Button)findViewById(R.id.b0);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b7 = (Button)findViewById(R.id.b7);
        b8 = (Button)findViewById(R.id.b8);
        headerText = (TextView)findViewById(R.id.header_text);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        //Logic for button press
        if(!activeG){
            return;
        }
        Button clickedb = (Button) findViewById(v.getId());
        int clickedtag = Integer.parseInt(v.getTag().toString());
       if(filledP[clickedtag] != -1){
           //Toast not working
           Toast.makeText(this,"INVALID.",Toast.LENGTH_SHORT).show();
           return;
       }
       filledP[clickedtag] = activeP;

        if(activeP == P_o){
            clickedb.setText("o");
            activeP = P_x;
            headerText.setText("Turn of X");
        }
        else{
            clickedb.setText("x");
            activeP = P_o;
            headerText.setText("Turn of O");
        }
        checkForWin();
    }
    private void checkForWin(){
        //Check for winner
        int[][] winningP = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i =0; i<8; i++){
            int val0 = winningP[i][0];
            int val1 = winningP[i][1];
            int val2 = winningP[i][2];
            if(filledP[val0] != -1 && filledP[val0] == filledP[val1] && filledP[val1] == filledP[val2]){
                if(filledP[val0] == P_o){
                    Dialog("O is winnwer");
                }
                else if(filledP[val0] == P_x){
                    Dialog("X is winnwer");
                }

                activeG = false;

            }
        }
        int i,x=0;
        if(activeG == true){
            for (i = 0; i < 9; i++){
                if(filledP[i] > -1){
                    x++;
                }
                if(x == 9){
                    Dialog("Draw");
                }
            }
        }
    }

    private void Dialog(String wintext){
        new AlertDialog.Builder(this)
                .setTitle(wintext)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();

    }
    private void restartGame(){
        activeP = P_o;
        headerText.setText("Turn of O");
        activeG = true;
        filledP = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        b0.setText("");
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
    }
}
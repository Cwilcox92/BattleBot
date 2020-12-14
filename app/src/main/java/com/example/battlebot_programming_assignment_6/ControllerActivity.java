package com.example.battlebot_programming_assignment_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.battlebot_programming_assignment_6.views.CustomJoystickView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerActivity extends AppCompatActivity {
    public Button shootBttn;
    public Button scanBttn;

    public Button upButton;
    public Button dwnButton;
    public Button leftButton;
    public Button rightButton;
    public Button upLeftButton;
    public Button upRightButton;
    public Button downLeftButton;
    public Button downRightButton;

    public int shotAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_layout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Objects.requireNonNull(getSupportActionBar()).setTitle("BattleBot Controller");
        MySocket socket = new MySocket();
        ExecutorService service= Executors.newFixedThreadPool(1);
        service.execute(()->{
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    socket.getLine();
                    socket.writeLine("noop");
                }
            }, 0, 600);//put here time 1000 milliseconds=1 second
        });
        shootBttn= findViewById(R.id.shootBttn);
        shootBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fireGun= "fire " + shotAngle;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine(fireGun);

                });
            }
        });
        scanBttn= findViewById(R.id.scanBttn);
        scanBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String s= "scan ";
                    socket.writeLine(s);

                });
            }
        });
        upButton= findViewById(R.id.upBttn);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle=0;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    //String moveUp= "move 0 -1" ;
                    socket.writeLine("move 0 -1");


                });
            }
        });
        dwnButton= findViewById(R.id.dwnBttn);
        dwnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle=180;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine("move 0 1");

                });
            }
        });
        leftButton= findViewById(R.id.leftBttn);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    shotAngle=270;
                    socket.writeLine("move -1 0");

                });
            }
        });
        rightButton= findViewById(R.id.rightBttn);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle=90;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine("move 1 0");

                });
            }
        });
        upRightButton= findViewById(R.id.upRightBttn);
        upRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle=45;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine("move 1 -1");

                });
            }
        });
        upLeftButton= findViewById(R.id.upLeftBttn);
        upLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle= 325;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{

                    socket.writeLine("move -1 -1");

                });
            }
        });
        downRightButton= findViewById(R.id.dwnRightBttn);
        downRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle=145;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine("move 1 1");

                });
            }
        });
        downLeftButton= findViewById(R.id.downLeftBttn);
        downLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotAngle= 225;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine("move -1 1");

                });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Exit:
                Toast.makeText(this,"exitgame seelected",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.About:
                return true;
            case R.id.Bot:
                Toast.makeText(this,"botabout seelected",Toast.LENGTH_SHORT).show();
                intent = new Intent(this, BotAbout.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
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
            //socket.writeLine("noop");
            socket.writeLine(socket.getLine());
        });
        shootBttn= findViewById(R.id.shootBttn);
        shootBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fireGun= "fire " + shotAngle;
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    socket.writeLine(fireGun);
                    socket.writeLine(socket.getLine());
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
                    socket.writeLine(socket.getLine());

                });
            }
        });
        upButton= findViewById(R.id.upBttn);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    //String moveUp= "move 0 -1" ;
                    socket.writeLine("move 0 -1");
                    socket.writeLine(socket.getLine());
                });
            }
        });
        dwnButton= findViewById(R.id.dwnBttn);
        dwnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveDown= "move " + 0 + " " + 1;
                    socket.writeLine(moveDown);
                    socket.writeLine(socket.getLine());
                });
            }
        });
        leftButton= findViewById(R.id.leftBttn);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveLeft= "move " + -1 + " " + 0;
                    socket.writeLine(moveLeft);
                    socket.writeLine(socket.getLine());
                });
            }
        });
        rightButton= findViewById(R.id.rightBttn);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveRight= "move " + 1 + " " + 0;
                    socket.writeLine(moveRight);
                    socket.writeLine(socket.getLine());
                });
            }
        });
        upRightButton= findViewById(R.id.upRightBttn);
        upRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveUpRight= "move " + -1 + " " + -1;
                    socket.writeLine(moveUpRight);
                    socket.writeLine(socket.getLine());
                });
            }
        });
        upLeftButton= findViewById(R.id.upLeftBttn);
        upLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveUpLeft= "move " + 1 + " " + -1;
                    socket.writeLine(moveUpLeft);
                    socket.writeLine(socket.getLine());
                });
            }
        });
        downRightButton= findViewById(R.id.dwnRightBttn);
        downRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveDownRight= "move " + 1 + " " + 1;
                    socket.writeLine(socket.getLine());
                });
            }
        });
        downLeftButton= findViewById(R.id.downLeftBttn);
        downLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service= Executors.newFixedThreadPool(1);
                service.execute(()->{
                    String moveDownLeft= "move " + -1 + " " + 1;
                    socket.writeLine(moveDownLeft);
                    socket.writeLine(socket.getLine());
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
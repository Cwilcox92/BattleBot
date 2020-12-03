package com.example.battlebot_programming_assignment_6;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.*;
import java.net.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


// Socket Class provided by Jim Ward, modified by Carlton Wilcox on 12/2/20
public class MySocket  {
    public static Socket socket= null;
    public static BufferedWriter out=null ;
    public static BufferedReader in = null;
    public BufferedReader stdIn;

    public void MakeClientCon(String host, int port){
        //     Making a connection to server, which is running on host at port
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Successfully made connection");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+ host);
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + host);
            System.err.println(e.toString());
        }
    }

    /* getLine
      @param  returns a Strings
      getLine reads the socket (blocking read) and returns the a line of text as
      the return value.  If the socket fails to read, "AWGH!!!" is returned
    */
    public String getLine() {
        // read from network port and have default value
        String from;
        System.out.println("Read start");
        try {
            from = in.readLine();
        } catch (IOException e) {
            from = "AWGH!!!";
        }
        System.out.println("Read Done");
        System.out.println("Message: "+from);
        return (from);
    }

    /*  writeLine
       @param   to  a string that to send via the socket
      Takes the variable to and sends to the receiving side of the socket
    */
    public void writeLine(String to) {
        System.out.println("Writing: "+to);
        //out.println(to);
        try {
            out.write(to,0,to.length());
            out.newLine();
            out.flush();
        } catch(IOException e) { System.out.println("Failed to write: "+e);}
        System.out.println("Writing done");
    }

}


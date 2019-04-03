package com.iauto.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        Thread fragment = new Thread(new fragment1());
        fragment.start();

    }
    public  static  class fragment1 implements Runnable {

        @Override
        public void run() {
            Search search = new Search();
            BufferedReader bufferedReader = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(30000);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true){

                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                    String string = bufferedReader.readLine();
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(search.run("jingdian",string).getBytes("utf-8"));

                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}



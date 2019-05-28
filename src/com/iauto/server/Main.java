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
        Thread addThread = new Thread(new fragmentadd());
        addThread.start();
        Thread orderHotel = new Thread(new orderHotelThread());
        orderHotel.start();
        Thread searchHotel = new Thread(new searchHotelThread());
        searchHotel.start();
        Thread searchPingjia = new Thread(new searchPingjiaThread());
        searchPingjia.start();
        Thread insertPingjia = new Thread(new insertPngjiaThread());
        insertPingjia.start();

    }
    //search jingdian Thread
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
                    outputStream.write(search.run("jingdian",string,0).getBytes("utf-8"));

                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //add jingdian Thread
    public  static  class fragmentadd implements Runnable{

        @Override
        public void run() {
            Insert insert = new Insert();
            BufferedReader bufferedReader = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(30001);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                    String string = bufferedReader.readLine();
                    insert.run(string,1);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //add orderHotel Thread
    private static class orderHotelThread implements Runnable {
        @Override
        public void run() {
            Insert insert = new Insert();
            BufferedReader bufferedReader = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(30002);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                    String string = bufferedReader.readLine();
                    insert.run(string,2);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //search jiuidan yudingqingkuang
    private static class searchHotelThread implements Runnable {
        @Override
        public void run() {
            Search search = new Search();
            BufferedReader bufferedReader = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(30003);
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
                    outputStream.write(search.run("orderhotel",string,1).getBytes("utf-8"));

                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //search jingdianpingjia
    private static class searchPingjiaThread implements Runnable {
        @Override
        public void run() {
            Search search = new Search();
            BufferedReader bufferedReader = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(30005);
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
                    outputStream.write(search.run("jingdianpingjia",string,2).getBytes("utf-8"));

                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static class insertPngjiaThread implements Runnable {
        @Override
        public void run() {
            Insert insert = new Insert();
            BufferedReader bufferedReader = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(30004);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                    String string = bufferedReader.readLine();
                    insert.run(string,3);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



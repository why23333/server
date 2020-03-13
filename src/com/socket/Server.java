package com.socket;

import java.io.IOException;
import java.net.ServerSocket;    
import java.net.Socket;
     

public class Server {
    public static void main(String[] args) {
        Socket socket=null;                    //定义接受用的socket
        try {
            ServerSocket serversocket=new ServerSocket(10006); //创建服务器类，设置端口，以连接客户端进行通信
            while(true) {
                System.out.println("服务器启动，等待客户端连接");
                socket=serversocket.accept();                   //接收客户端传来的socket，赋值给本地socket
                System.out.println("concet success");         //显示是否连接成功
                ServerThread serverthread=new ServerThread(socket); //创建多线程类把socket传入
                serverthread.start();                               
            }
        } catch (IOException e) {
                // TODO Auto-generated catch block
            e.printStackTrace();
        }          
    }
}
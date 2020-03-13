package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
 
public class ServerThread extends Thread{     
	static int white=0;
	static int black=0;
	int temp=0;
	Socket socket=null;                    //定义本地的socket
	public ServerThread(Socket socket) {  
		this.socket=socket;
	}
	public void run() {              
		try {
			InputStream is=socket.getInputStream();  //定义输入流用来接收socket的
			InputStreamReader isr=new InputStreamReader(is);//把字节流转成字符流
			BufferedReader br=new BufferedReader(isr);  
			OutputStream os=socket.getOutputStream();     //得到outputStream对象
	        PrintWriter pw=new PrintWriter(os); 
			String  str=null;
			//System.out.println(br.readLine());
			
			str=br.readLine();
			{  //把从客户端传来的信息赋给str
				System.out.println(str);
				if(str.charAt(0)=='R')
				{
					int i=0,j=0;
					while(i<str.length()&&(str.charAt(i)<'0'||str.charAt(i)>'9'))
						i++;
					if(i!=str.length())
					{
					    temp=str.charAt(i)-48;
					    while(i<str.length()-1)
					    {
						    i++;
						    temp+=temp*10+str.charAt(i)-48;
					    }
					    white+=temp;
					//System.out.println(j);
					//white=str.charAt(str.length()-1)-48;
					    System.out.println(white);
					    System.out.println(black);
					         //转换成字符流
			            pw.write(Integer.toString(black)+'\n');   
					}//把字符串传给服务器
		
				}
				else
				{
					int i=0,j=0;
					while(i<str.length()&&(str.charAt(i)<'0'||str.charAt(i)>'9'))
						i++;
					if(i!=str.length())
					{
					    temp=str.charAt(i)-48;
					    while(i<str.length()-1)
					    {
						    i++;
						    temp=temp*10+str.charAt(i)-48;
					    }
					    black+=temp;
					    System.out.println(white);
					    System.out.println(black);
					//black=str.charAt(str.length()-1)-48;//转换成字符流
			            pw.write(Integer.toString(white)+'\n'); 
					}//把字符串传给服务器
			       
				}
				
				//System.out.println(str);  //输出信息
			}
			pw.flush();
		    os.close();
		    pw.close(); //关闭socket
                            is.close();
                            isr.close();
                            br.close();
            socket.close();
                         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {	
		}
	}
}
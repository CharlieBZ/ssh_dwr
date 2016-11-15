package com.java_socket01.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端
 * @author 李明
 * @time 2016年11月9日 下午1:32:29
 */
public class SocketServer {
	private List<ServerThread> clients = null;
	public static void main(String[] args) {
		System.out.println("启动服务......");
		new SocketServer().startSocketServer();
	}
	
	public void startSocketServer(){
		//1.创建SocketServer对象
		try {
			ServerSocket server = new ServerSocket(1234);
			//2.创建一个用于维护客户端线程的集合
			clients = new ArrayList<ServerThread>();
			//1.循环侦听
			while(true){
				Socket socket = server.accept();
				Thread thread = new Thread(new ServerThread(socket));
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ServerThread implements Runnable{
		private Socket socket;
		private BufferedReader read = null;
		private PrintWriter writer = null;
		private String name = null;
		private boolean flag = false;
		public ServerThread(Socket socket){
			this.socket = socket;
			try {
				read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				//用户的唯一标识，此处用ip地址标识
				name = "["+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"]";
				//添加到集合中
				clients.add(this);
				sendMsg(name+"上线了");
				receiveMsg();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(!flag){
				
			}
		}
		
		//向客户端群发消息
		public void sendMsg(String msg){
			System.out.println(msg);
			for(ServerThread st:clients){
				st.writer.println(msg);
			}
		}
		
		//读取消息
		public void receiveMsg(){
			try {
				String servcerMsg = read.readLine();
				System.out.println("----------"+servcerMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//停止当前线程
		public void stop(){
			
		}
		
	}
}

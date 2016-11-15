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
 * �����
 * @author ����
 * @time 2016��11��9�� ����1:32:29
 */
public class SocketServer {
	private List<ServerThread> clients = null;
	public static void main(String[] args) {
		System.out.println("��������......");
		new SocketServer().startSocketServer();
	}
	
	public void startSocketServer(){
		//1.����SocketServer����
		try {
			ServerSocket server = new ServerSocket(1234);
			//2.����һ������ά���ͻ����̵߳ļ���
			clients = new ArrayList<ServerThread>();
			//1.ѭ������
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
				//�û���Ψһ��ʶ���˴���ip��ַ��ʶ
				name = "["+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"]";
				//��ӵ�������
				clients.add(this);
				sendMsg(name+"������");
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
		
		//��ͻ���Ⱥ����Ϣ
		public void sendMsg(String msg){
			System.out.println(msg);
			for(ServerThread st:clients){
				st.writer.println(msg);
			}
		}
		
		//��ȡ��Ϣ
		public void receiveMsg(){
			try {
				String servcerMsg = read.readLine();
				System.out.println("----------"+servcerMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//ֹͣ��ǰ�߳�
		public void stop(){
			
		}
		
	}
}

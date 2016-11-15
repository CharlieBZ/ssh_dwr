package com.java_socket01.main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) {
		Socket s;
		try {
			s = new Socket("127.0.0.1",1234);
			//构建IO
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			//向服务器端发送一条消息
			bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
			bw.flush();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

}

package robotics.chase.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientCommunication {
	// initializing variables
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	public ClientCommunication(String address,int port) {
		try 
		{
			socket = new Socket(address,port);
			System.out.println("Successful connection");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch(IOException e1) 
		{
			System.out.println(e1);
		}
	}
	
	public void writeMessage(String mssg)
	{
		try
		{
			writer.write(mssg);
			writer.newLine();
			writer.flush();
		}
		catch(IOException e)
		{}
	}
	
	public String waitForMessage()
	{
		System.out.println("waiting for message");
		String mssg = null;
		try
		{
			while((mssg = reader.readLine()) == null) 
			{
				//Waiting for message
			}
			System.out.println(mssg);
			return mssg;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mssg;
	}
	
	public void closeSocket()
	{
		try
		{
			writeMessage("CLOSE");
			socket.close();
		}
		catch(IOException e){}
	}
	
	/*public static void main(String args[]) throws IOException 
	{
		ClientCommunication client = new ClientCommunication("192.168.2.21",6852); 
		client.writeMessage("poop");
		client.waitForMessage();
		client.closeSocket();
	}*/
}
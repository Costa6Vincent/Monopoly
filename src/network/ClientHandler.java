/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler
{
	public static boolean connected = false;
	public static Point postPoints = null;
	public static Point initPoints = null;
	private static String hostIP = null;
	private static int hostPort = -1;
	private static Socket server = null;
	private static PrintWriter serverOut = null;
	private static BufferedReader serverIn = null;

	public static void connect(String ip, int port) throws UnknownHostException, IOException
	{
		hostIP = ip;
		hostPort = port;
		server = new Socket();
		server.connect(new InetSocketAddress(ip, port), 6000);
		serverOut = new PrintWriter(server.getOutputStream(), true);
		serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
		connected = true;
		recievePieceMove();
	}

	public static void disconnect()
	{
		try
		{
			if (server != null)
				server.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostIP = null;
		hostPort = -1;
		server = null;
		serverOut = null;
		serverIn = null;
		connected = false;
		monopoly.project.MonopolyProject.gameStarted = false;
//		monopoly.project.MonopolyProject.reset();
	}
    public static void sendPieceMove(int val)
    {
		if (connected)
		{
//add or modify.                    
			serverOut.println(-1 + ":" + val);
			monopoly.project.MonopolyProject.myTurn = false;
		}        
    }

	public static void sendDisconnect()
	{
		if (connected)
		{
			serverOut.println("esc");
		}
	}


	private static void recievePieceMove()
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				String inputLine;

				try
				{
					while ((inputLine = serverIn.readLine()) != null)
					{
						try
						{
							if (inputLine.equals("esc"))
							{
								disconnect();
								return;
							}
//add or modify.
							// row:col:initrow:initcol
							int ypost = Integer.parseInt(inputLine.split(":")[0]);
							int xpost = Integer.parseInt(inputLine.split(":")[1]);
                                                        
                                                        monopoly.project.MonopolyProject.serverValue=ypost;
                                                        monopoly.project.MonopolyProject.myTurn = true;
						}
						catch (NumberFormatException e)
						{
							e.printStackTrace();
						}
						catch (NullPointerException e)
						{
							disconnect();
						}
					}
				}
				catch (IOException e)
				{
					disconnect();
				}

			}
		}).start();
	}

	public static boolean isConnected()
	{
		return connected;
	}
}

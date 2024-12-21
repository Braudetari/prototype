// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import ocsf.server.*;
import server.ConnectionToClientInfo.ClientConnectionStatus;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class BLibServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  //final public static int DEFAULT_PORT = 5555;
  private static ArrayList<ConnectionToClientInfo> connections = new ArrayList<ConnectionToClientInfo>();
  private static Thread threadPing;
  private boolean flagKillPingThread = false;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * 
   */

  public BLibServer(int port) 
  {
    super(port);
  }

  //Instance methods ************************************************
  
  //return connections as readonly for viewing
  public List<ConnectionToClientInfo> getClientConnectionsList(){
	  return Collections.unmodifiableList(connections);
  }
  
  private void handleClientConnection(ConnectionToClient client) {
	  boolean clientExists = false;
	  int clientIndex = -1;
	  for(int i=0; i<connections.size() && clientExists == false; i++) {
		  ConnectionToClientInfo clientInfo = connections.get(i);
		  if(clientInfo.equals(client)) {
			  clientExists = true;
			  clientIndex = i;
		  }
	  }
	  if(!clientExists) {
		  connections.add(new ConnectionToClientInfo(client));
	  }
	  else {
		  if(clientIndex >= 0) {
			  connections.get(clientIndex).setClient(client);
			  connections.get(clientIndex).setStatus(ClientConnectionStatus.Connected);
		  }
	  }
  }
  
  private void handleClientDisconnection(ConnectionToClientInfo clientInfo) {
	  clientInfo.setStatus(ClientConnectionStatus.Disconnected);
  }
  
  /**
   * Pings all clients known for isAlive()
   * If fails MAX_TIMEOUTS in a row will disconnect client
   * @throws InterruptedException
   */
  private void pingConnections() throws InterruptedException {
	  while(true) {
		  if(flagKillPingThread)
			  	return; 
		  int connectionsSize = connections.size();
		  for(int i=0; i<connectionsSize; i++) {
			  ConnectionToClientInfo clientInfo = connections.get(i);
			  if(!clientInfo.getClient().isAlive() && clientInfo.getStatus() == ClientConnectionStatus.Connected){
				  handleClientDisconnection(clientInfo);
				  connectionsSize--;
			  }
		  }
		  Thread.sleep(1000);
	  }
	  
  }
  
  public void handleMessageToClient(Object msg, ConnectionToClient client) {
	  try {
		  client.sendToClient(msg);
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   * @param 
   */
  public void handleMessageFromClient  (Object msg, ConnectionToClient client)
  {
	 System.out.println("Message received: " + msg + " from " + client);
	 
	 if(msg.equals("connect")) {
		 handleClientConnection(client);
		 handleMessageToClient("msg connected to server", client);
	 }
	 else {
		 handleMessageToClient("echo: " + msg, client);
	 }
  }
   
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println ("Server listening for connections on port " + getPort());
    //Start pinging all known and new connections
    threadPing = new Thread(() -> {
		try {
			pingConnections();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});
    threadPing.start();
  }
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()  {
    System.out.println ("Server has stopped listening for connections.");
    flagKillPingThread = true;
  }  
}
//End of EchoServer class

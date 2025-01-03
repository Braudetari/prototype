// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import client.*;
import common.ChatIF;
import common.Message;
import common.Subscriber;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  public static boolean awaitResponse = false;
  public ConnectionStatus status;
  public static enum ConnectionStatus{Disconnected, Connected}; 
  private List<Subscriber> subscriberList;
  private Subscriber subscriber;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
	 
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    this.status = ConnectionStatus.Disconnected;
    //openConnection();
  }

  //More Methods
  
  public List<Subscriber> getSubscriberList(){
	  if(subscriberList == null) {
		  return null;
	  }
	  return Collections.unmodifiableList(subscriberList);
  }
  public Subscriber getSubscriber(){
	  if(subscriber == null) {
		  return null;
	  }
	  return (subscriber);
  }
  //Instance methods ************************************************
   
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  System.out.println("--> " + msg);
	  String[] inputs = msg.toString().split(" ");
	  
	  if(inputs[0] != null) {
		  switch(inputs[0]) {
		  	case "subscribers":
		  			subscriberList = Subscriber.subscriberListFromString(Message.decryptFromBase64(inputs[1]));
		  		break;
		  	case "getsubscriber":
		  		
		  		subscriber=Subscriber.subscriberFromString(Message.decryptFromBase64(inputs[1]));
		  	case "requestConnect":
					try {
						sendToServer("connect " + Message.encryptToBase64(InetAddress.getLocalHost().getHostName()));
					} catch (Exception e) {
						e.printStackTrace();
					}
		  		break;
		  	default:
		  		break;
		  }
			  
	  }
	  
	  awaitResponse = false;
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  
  public void handleMessageFromClientUI(String message)  
  {
    try
    {
    	openConnection();//in order to send more than one message
       	awaitResponse = true;
    	sendToServer(message);
		// wait for response
		while (awaitResponse) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		status = ConnectionStatus.Connected;
    }
    catch(IOException e)
    {
    	e.printStackTrace();
      clientUI.display("Could not send message to server: Terminating client."+ e);
      quit();
    }
  }

  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    //System.exit(0);
  }
}
//End of ChatClient class

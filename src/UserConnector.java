import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserConnector extends Thread
{
  private Socket clientSocket;
  private int id;
  
  UserConnector(Socket clientSocket ){
    this.clientSocket = clientSocket;
    id = clientSocket.hashCode();
  }
  
  @Override
  public void run()
  {
    try(   PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true );
           BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ))) {
      
      out.println( "Hello client, I am server" );
      String inputLine = in.readLine();
      System.out.println( inputLine );

      while ( ( inputLine = in.readLine() ) != null ) {
        if(getLastId()!=id) {
          out.println( getLastMessage() );
        }
        updateLastMessage( inputLine );
      }
   
    } catch( IOException e ) {
      e.printStackTrace();
    } 
    
  }
  
  synchronized void updateLastMessage(String message){
    Server.lastMessage = message;
    Server.lastId = this.id;
  }
  
  synchronized String getLastMessage(){
    return Server.lastMessage;
  }
  
  synchronized int getLastId(){
    return Server.lastId;
  }
  
}

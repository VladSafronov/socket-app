import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Server
{
  static volatile int lastId;
  static volatile List<String> messageHistory;
  static volatile String lastMessage;
  
  public static void main( String[] args )
  {
    int portNumber = 8000;
    
    try(ServerSocket serverSocket = new ServerSocket(portNumber  )) {

      while ( true ) {
        Socket clientSocket = serverSocket.accept();
        UserConnector userConnector = new UserConnector(clientSocket);
        userConnector.start();
      } 
      
    } catch( IOException e ) {
      e.printStackTrace();
    }


  }
}

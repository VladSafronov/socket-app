import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by vladsafronov on 5.12.17.
 */
public class Client
{
  
  public static void main( String[] args )
  {
    String hostName = "localhost";
    int portNumber = 8000;
    Scanner scan = new Scanner(System.in);
    
    try(
      Socket socket = new Socket( hostName,portNumber );
      PrintWriter out = new PrintWriter( socket.getOutputStream(),true );
      BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) )
      ) {
      
      out.println("Hello server, I am client");
      
      System.out.println(in.readLine());
      String userInput;
      System.out.println("Please input smt:");
      while((userInput = scan.nextLine()) != null) {
        out.println(userInput);
        System.out.println(in.readLine());
      }
      
      
    } catch( UnknownHostException e ) {
      e.printStackTrace();
    } catch( IOException e ) {
      e.printStackTrace();
    }
  }
  
  
}

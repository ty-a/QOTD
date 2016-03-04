import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


public class QOTDProtocolHandler implements Runnable {
	private Socket clientSocket;
	
	QOTDProtocolHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	@Override
	public void run() {
		PrintWriter out = null;
		try	{
			out = new PrintWriter(clientSocket.getOutputStream(), true);
				
			String output;

			output = "\"BDSM reminds me of database management\"";
			
			out.printf("%s", output);
			
		} catch(SocketException e) {
			System.out.println("Connection lost to a client! Error message " + e.getMessage());
			
		} catch (IOException e) {
			System.out.println("IOException: Connection with client closed.");
		} finally {
			
			if(clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException e) {}
			}
			
				
			if(out != null) {
				out.close();
			}
		}
	}
}

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


public class QOTDProtocolHandler implements Runnable {
	private Socket clientSocket;
	private QOTDDBHandler db;
	
	QOTDProtocolHandler(Socket clientSocket, QOTDDBHandler db) {
		this.clientSocket = clientSocket;
		this.db = db;
	}
	@Override
	public void run() {
		PrintWriter out = null;
		try	{
			out = new PrintWriter(clientSocket.getOutputStream(), true);
				
			//String output;

			//output = "\"BDSM reminds me of database management\"";
			
			out.printf("%s%n", db.getQuote());
			
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

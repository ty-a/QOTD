import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		Server server = new Server();
		server.start();
	}
	
	public void start() {
		ServerSocket serverSocket = null;
		QOTDDBHandler db = new QOTDDBHandler();
		try	{
			serverSocket = new ServerSocket(17);

			while(true) {
				// Allow us to handle multiple connections
				//     Downside is only way to terminate server is with Ctrl + C
				Socket currSocket = serverSocket.accept();
				System.out.println("Recieved connection from: " + currSocket.getRemoteSocketAddress());
				QOTDProtocolHandler ucph = new QOTDProtocolHandler(currSocket, db);
				Thread newConnection = new Thread(ucph);
				newConnection.start();
			}
			
		} catch (IOException e) {
			System.out.println("Failed to create sockets and their readers or had a different error.");
			System.out.println(e.getClass() + e.getMessage());
			System.exit(1);
		} finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {}
			}			
		}

	}
}
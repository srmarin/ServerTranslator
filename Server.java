//======================================================================//
//				SERVER.JAVA				//
//======================================================================//

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int port = 8080;
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(port);
			do {
				socket = serverSocket.accept();
				Processing procesador=new Processing(socket);
				procesador.start();
			} while (true);
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}

}

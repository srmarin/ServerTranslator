//======================================================================//
//				SERVER.JAVA				//
//======================================================================//

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int port = 8989;
		ServerSocket serverSocket = null;
		Socket socket = null;
		initializeDB();
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

	static void initializeDB(){
		String fileName = "database.txt";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			for (int i=0;i<10;i++) writer.write(i+" 0\n");
			writer.close();         
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}

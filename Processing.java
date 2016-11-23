// ==================================================== //
// 			Processing 			//
// ==================================================== //

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Processing extends Thread {
	private Socket socketService;
	private InputStream inputStream;
	private OutputStream outputStream;
	//Get entries from external file
	private static final String fileName = "bd.txt";

	public Processing(Socket mySocket) {
		this.socketService=mySocket;
	}

	void busca(){
		String stringReceived,translation;
		try {
			// Streams
			inputStream = socketService.getInputStream();
			outputStream = socketService.getOutputStream();
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			PrintWriter outPrinter = new PrintWriter(outputStream, true);

			do {
				stringReceived = inReader.readLine();
				stringReceived = stringReceived.toLowerCase();
				translation = readBD(stringReceived);
				outPrinter.println(translation);
			} while (true);
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}

	String readBD(String word){
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			while((line = bufferedReader.readLine()) != null) {
				String[] s = line.split(":");
				if(s[0].equals(word)){
					return s[1];
				}
				else if(s[1].equals(word)){
					return s[0];
				}
			}
			bufferedReader.close();
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public void run(){
		busca();
	}
}

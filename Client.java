//======================================================================//
//			CLIENT.JAVA					//
//======================================================================//
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		String palabraEnviar,palabraTraducida;
		int productID=0, port=8080;
		String host="localhost";
		Socket socketService=null;

		try {
			socketService = new Socket(host, port);
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
		try {
			// Streams
				InputStream inputStream = socketService.getInputStream();
				OutputStream outputStream = socketService.getOutputStream();
				PrintWriter outPrinter = new PrintWriter(outputStream, true);
				BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));

				System.out.println("Introduce una palabra: ");
				Scanner sc = new Scanner(System.in);
				palabraEnviar = sc.nextLine();
				outPrinter.println(palabraEnviar);
				outPrinter.flush();

				palabraTraducida = inReader.readLine();
				System.out.println("Su traduccion es: "+palabraTraducida);

				System.out.println("#############");
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error .");
		}

		// Close the socket
		try {
			socketService.close();
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}

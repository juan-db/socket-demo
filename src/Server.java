import java.net.*;
import java.io.*;

public class Server {
	public static final int PORT = 5000;

	public static void main(String[] args) {
		try (
				// Server socket
				ServerSocket serverSocket = new ServerSocket(PORT);

				// Client socket
		        Socket clientSocket = serverSocket.accept();

				// Input from client
				InputStreamReader clientInput = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader bufferedClientInput = new BufferedReader(clientInput);

		        // Client output stream
				OutputStreamWriter clientOutput = new OutputStreamWriter(clientSocket.getOutputStream());
				BufferedWriter bufferedClientOutput = new BufferedWriter(clientOutput);
		) {
			System.out.println("Listening to client: " + clientSocket.toString());

			String inputLine;
			while ((inputLine = bufferedClientInput.readLine()) != null) {
				bufferedClientOutput.write("request: ");
				bufferedClientOutput.write(inputLine);
				bufferedClientOutput.newLine();
				bufferedClientOutput.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

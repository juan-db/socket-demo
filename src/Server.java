import java.net.*;
import java.io.*;

public class Server {
	public static final int PORT = 5000;

	public static void main(String[] args) {
		try (
				// Server socket.
				ServerSocket serverSocket = new ServerSocket(PORT);

				// Wait for a client to connect and get its socket.
				Socket clientSocket = serverSocket.accept();

				// Input from client.
				InputStreamReader clientInput = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader bufferedClientInput = new BufferedReader(clientInput);

				// Output to client.
				OutputStreamWriter clientOutput = new OutputStreamWriter(clientSocket.getOutputStream());
				BufferedWriter bufferedClientOutput = new BufferedWriter(clientOutput);
		) {
			// Print out the client the server is connected to.
			System.out.println("Listening to client: " + clientSocket.toString());

			// 1. Get input from the client.
			// 2. Send response.
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

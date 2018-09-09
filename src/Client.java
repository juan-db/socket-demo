import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private static final int CLIENT_PORT = 5000;

	public static void main(String[] args) {
		try (
				// Client socket
				Socket client = new Socket(InetAddress.getLocalHost(), CLIENT_PORT);

				// Socket input from server
				InputStreamReader clientInputReader = new InputStreamReader(client.getInputStream());
				BufferedReader bufferedClientInputReader = new BufferedReader(clientInputReader);

				// Socket output to server
				OutputStreamWriter clientOutputWriter = new OutputStreamWriter(client.getOutputStream());
				BufferedWriter bufferedClientOutputWriter = new BufferedWriter(clientOutputWriter);

				// stdin
				InputStreamReader stdinInputReader = new InputStreamReader(System.in);
				BufferedReader bufferedStdinInputReader = new BufferedReader(stdinInputReader)
		) {
			System.out.println("Created client: " + client.toString());
			String userInput;
			while ((userInput = promptInput(bufferedStdinInputReader)) != null) {
				// Write output to server socket
				bufferedClientOutputWriter.write(userInput);
				bufferedClientOutputWriter.newLine();
				bufferedClientOutputWriter.flush();

				// Get response from server
				System.out.println(bufferedClientInputReader.readLine());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static String promptInput(BufferedReader reader) throws IOException {
		System.out.print("> ");
		return reader.readLine();
	}
}

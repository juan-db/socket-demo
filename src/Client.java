import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private static final int CLIENT_PORT = 5000;

	public static void main(String[] args) {
		try (
				// Client socket.
				Socket client = new Socket(InetAddress.getLocalHost(), CLIENT_PORT);

				// Client's input stream. Data sent from the server this socket is connected to gets written into this
				// stream.
				InputStreamReader clientInputReader = new InputStreamReader(client.getInputStream());
				BufferedReader bufferedClientInputReader = new BufferedReader(clientInputReader);

				// Client's output stream. Data written to this stream will get sent to the server this socket is
				// connected to.
				OutputStreamWriter clientOutputWriter = new OutputStreamWriter(client.getOutputStream());
				BufferedWriter bufferedClientOutputWriter = new BufferedWriter(clientOutputWriter);

				// stdin stream reader to get user input.
				InputStreamReader stdinInputReader = new InputStreamReader(System.in);
				BufferedReader bufferedStdinInputReader = new BufferedReader(stdinInputReader)
		) {
			// Print out the client's socket information.
			System.out.println("Created client: " + client.toString());

			// 1. Prompt for input from stdin.
			// 2. Send the input to the server.
			// 3. Print the server's response.
			String userInput;
			while ((userInput = promptInput(bufferedStdinInputReader)) != null) {
				// Write output to server socket.
				bufferedClientOutputWriter.write(userInput);
				bufferedClientOutputWriter.newLine();
				bufferedClientOutputWriter.flush();

				// Print out server's response.
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

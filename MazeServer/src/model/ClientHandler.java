package model;

import java.io.InputStream;
import java.io.OutputStream;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClientHandler.
 */
public interface ClientHandler {

	/**
	 * Handle client.
	 *
	 * @param inFromClient the in from client
	 * @param outToServer the out to server
	 */
	public void handleClient(InputStream inFromClient, OutputStream outToServer);
}

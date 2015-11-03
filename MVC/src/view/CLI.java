package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Controller;
import controller.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI {


	/** The in. */
	private BufferedReader in;
	
	/** The out. */
	private PrintWriter out;
	
	/** The Commands. */
	HashMap<String, Command> Commands;

	/**
	 * Instantiates a new cli.
	 *
	 * @param buf the buf
	 * @param pw the pw
	 * @param c the c
	 */
	public CLI(BufferedReader buf,PrintWriter pw,HashMap<String, Command> c ){
		this.in=buf;
		this.out=pw;
		this.Commands=c;
	}

	/**
	 * Start.
	 *
	 * @param c the c
	 */
	public void start(Controller c){

		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{

				System.out.print("Enter command (for cmd list, type \"menu\"): ");
				try {
					String line = in.readLine();

					while (!line.equals("exit")) {

						c.invokeCommand(line);
						System.out.print("Enter command: ");
						line = in.readLine();
					}

					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						System.out.println("bye bye");
						in.close();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
			}).start();
	}
}
package controller;

import java.util.HashMap;


// TODO: Auto-generated Javadoc
/**
 * The Interface Controller.
 */
public interface Controller {
	
	/**
	 * Display.
	 *
	 * @param s the s
	 */
	public void display(String s);
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public HashMap<String, Command> getMap();
	
	/**
	 * Invoke command.
	 *
	 * @param command the command
	 */
	public void invokeCommand(String command);
		
}	
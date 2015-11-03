package controller;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Interface Command.
 */
public interface Command {

	/**
	 * Do command.
	 *
	 * @param command the command
	 * @param v the v
	 * @param m the m
	 */
	void doCommand(String command, View v, Model m);
}
